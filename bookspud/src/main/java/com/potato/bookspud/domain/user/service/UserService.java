package com.potato.bookspud.domain.user.service;

import com.potato.bookspud.domain.auth.dto.response.KakaoUserResponse;
import com.potato.bookspud.domain.auth.dto.response.LoginSuccessResponse;
import com.potato.bookspud.domain.auth.handler.UserAuthentication;
import com.potato.bookspud.domain.auth.jwt.JwtTokenProvider;
import com.potato.bookspud.domain.auth.jwt.redis.RefreshTokenService;
import com.potato.bookspud.domain.user.domain.User;
import com.potato.bookspud.domain.user.dto.response.AccessTokenGetSuccess;
import com.potato.bookspud.domain.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;
    private final RefreshTokenService refreshTokenService;

    public Long createUser(final KakaoUserResponse userResponse) {
        User user = User.of(
                userResponse.kakaoAccount().profile().nickname(),
                userResponse.kakaoAccount().profile().profileImageUrl(),
                userResponse.kakaoAccount().profile().accountEmail(),
                userResponse.id()
        );
        return userRepository.save(user).getId();
    }

    public Long getIdBySocialId(final Long socialId) {
        User user = userRepository.findBySocialId(socialId).orElseThrow(
                () -> new IllegalArgumentException("해당하는 사용자가 존재하지 않습니다.")
        );
        return user.getId();
    }

    public AccessTokenGetSuccess refreshToken(final String refreshToken) {
        Long userId = jwtTokenProvider.getUserFromJwt(refreshToken);
        if (!userId.equals(refreshTokenService.findIdByRefreshToken(refreshToken))) {
            throw new IllegalArgumentException("토큰 오류");
        }
        UserAuthentication userAuthentication = new UserAuthentication(userId, null, null);
        String newAccessToken = jwtTokenProvider.issueAccessToken(userAuthentication);
        String newRefreshToken = jwtTokenProvider.issueRefreshToken(userAuthentication);
        return AccessTokenGetSuccess.of(newAccessToken, newRefreshToken);
    }

    public boolean isExistingUser(final Long socialId) {
        return userRepository.findBySocialId(socialId).isPresent();
    }

    public LoginSuccessResponse getTokenByUserId(final Long id) {
        UserAuthentication userAuthentication = new UserAuthentication(id, null, null);
        String refreshToken = jwtTokenProvider.issueRefreshToken(userAuthentication);
        refreshTokenService.saveRefreshToken(id, refreshToken);
        return LoginSuccessResponse.of(
                jwtTokenProvider.issueAccessToken(userAuthentication),
                refreshToken
        );
    }

    @Transactional
    public void deleteUser(final Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 사용자가 존재하지 않습니다."));
        userRepository.delete(user);
    }
}
