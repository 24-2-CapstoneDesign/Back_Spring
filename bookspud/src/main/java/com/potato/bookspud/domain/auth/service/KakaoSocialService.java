package com.potato.bookspud.domain.auth.service;

import com.potato.bookspud.domain.auth.client.KakaoApiClient;
import com.potato.bookspud.domain.auth.client.KakaoAuthApiClient;
import com.potato.bookspud.domain.auth.dto.response.KakaoAccessTokenResponse;
import com.potato.bookspud.domain.auth.dto.response.KakaoUserResponse;
import com.potato.bookspud.domain.auth.dto.response.LoginSuccessResponse;
import com.potato.bookspud.domain.auth.jwt.redis.RefreshTokenService;
import com.potato.bookspud.domain.user.service.UserService;
import feign.FeignException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KakaoSocialService {

    private static final String AUTH_CODE = "authorization_code";
    private static final String REDIRECT_URI = "http://localhost:8080/api/login/kakao";

    @Value("${spring.security.oauth2.client.registration.kakao.client-id}")
    private String clientId;

    @Value("${spring.security.oauth2.client.registration.kakao.client-secret}")
    private String clientSecret;

    private final KakaoApiClient kakaoApiClient;
    private final KakaoAuthApiClient kakaoAuthApiClient;
    private final UserService userService;
    private final RefreshTokenService refreshTokenService;

    @Transactional
    public LoginSuccessResponse login(final String accessToken) {
//        if (authorizationCode == null || authorizationCode.isEmpty()) {
//            throw new IllegalArgumentException("Authorization code is null or empty.");
//        }
//        String accessToken;
//        try {
//            accessToken = getOAuth2Authentication(authorizationCode);
//        } catch (FeignException e) {
//            throw new IllegalArgumentException("Failed to get access token with authorization code: " + authorizationCode, e);
//        }

        try {
            KakaoUserResponse userResponse = getUserInfo(accessToken);
            refreshTokenService.saveRefreshToken(userResponse.id(), accessToken);
            return processUser(userResponse);
        } catch (FeignException e) {
            throw new IllegalArgumentException("Failed to get user information with access token: " + accessToken, e);
        }
    }

    private String getOAuth2Authentication(final String authorizationCode) {
        KakaoAccessTokenResponse tokenResponse = kakaoAuthApiClient.getOAuth2AccessToken(
                AUTH_CODE, clientId, REDIRECT_URI, authorizationCode, clientSecret);
        return tokenResponse.accessToken();
    }

    private KakaoUserResponse getUserInfo(final String accessToken) {
        return kakaoApiClient.getUserInformation("Bearer " + accessToken);
    }

    private LoginSuccessResponse processUser(KakaoUserResponse userResponse) {
        if (userService.isExistingUser(userResponse.id())) {
            return userService.getTokenByUserId(userService.getIdBySocialId(userResponse.id()));
        } else {
            return userService.getTokenByUserId(userService.createUser(userResponse));
        }
    }

    public KakaoUserResponse getKakaoUser(final String authorizationCode, final String redirectUri) {
        String accessToken = getOAuth2Authentication(authorizationCode);
        return kakaoApiClient.getUserInformation("Bearer " + accessToken);
    }
}
