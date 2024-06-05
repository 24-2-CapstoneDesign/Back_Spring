package com.potato.bookspud.domain.user.service;

import com.potato.bookspud.domain.auth.client.KakaoAccount;
import com.potato.bookspud.domain.auth.client.KakaoUserProfile;
import com.potato.bookspud.domain.auth.dto.response.KakaoUserResponse;
import com.potato.bookspud.domain.auth.dto.response.LoginSuccessResponse;
import com.potato.bookspud.domain.auth.handler.UserAuthentication;
import com.potato.bookspud.domain.auth.jwt.JwtTokenProvider;
import com.potato.bookspud.domain.auth.jwt.redis.RefreshTokenService;
import com.potato.bookspud.domain.common.ErrorCode;
import com.potato.bookspud.domain.s3.service.S3Service;
import com.potato.bookspud.domain.user.domain.User;
import com.potato.bookspud.domain.user.dto.request.NicknameRegisterRequest;
import com.potato.bookspud.domain.user.dto.request.ProfileRegisterRequest;
import com.potato.bookspud.domain.user.dto.response.AccessTokenGetSuccess;
import com.potato.bookspud.domain.user.exception.InvalidUserException;
import com.potato.bookspud.domain.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import java.util.Optional;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class UserService {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;
    private final RefreshTokenService refreshTokenService;
    private final S3Service s3Service;

    public Long createUser(final KakaoUserResponse userResponse) {

        String birthyear = Optional.ofNullable(userResponse.kakaoAccount())
                .map(KakaoAccount::profile)
                .map(KakaoUserProfile::birthyear)
                .orElse(null);
        User user = User.of(birthyear, userResponse.id());
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

    public User getUserFromAccessToken(String accessToken) {
        Long userId = jwtTokenProvider.getUserFromJwt(accessToken);
        // id 값으로 user 찾기
        return userRepository.findById(userId)
                // 임시 exception 처리
                .orElseThrow(() -> new UsernameNotFoundException("User not found with id"));
    }

    @Transactional
    public void deleteUser(User user) {
        userRepository.findById(user.getId()).orElseThrow(() -> new IllegalArgumentException("해당하는 사용자가 존재하지 않습니다."));
        userRepository.delete(user);
    }

    public void registerNickname(NicknameRegisterRequest nicknameRegisterRequest, User user) {
        userRepository.findById(user.getId()).orElseThrow(() -> new InvalidUserException(ErrorCode.NOT_FOUND_USER_EXCEPTION));
        user.setNickname(nicknameRegisterRequest.nickname());
        userRepository.save(user);
    }

    public String registerProfileImage(ProfileRegisterRequest profileRegisterRequest, User user) throws IOException {
        userRepository.findById(user.getId()).orElseThrow(()-> new InvalidUserException(ErrorCode.NOT_FOUND_USER_EXCEPTION));
        String profileImageUrl = s3Service.uploadImage(profileRegisterRequest.image());
        user.setProfileImageUrl(profileImageUrl);
        userRepository.save(user);
        return profileImageUrl;
    }

    public Integer getPoint(User user) {
        userRepository.findById(user.getId()).orElseThrow(() -> new InvalidUserException(ErrorCode.NOT_FOUND_USER_EXCEPTION));
        return user.getPoint();
    }

    public void updatePoint(Integer delta, User user) {
        userRepository.findById(user.getId()).orElseThrow(() -> new InvalidUserException(ErrorCode.NOT_FOUND_USER_EXCEPTION));
        Integer currentPoint = user.getPoint();
        Integer updatedPoint = currentPoint + delta;
        user.setPoint(updatedPoint);
        userRepository.save(user);
    }
}
