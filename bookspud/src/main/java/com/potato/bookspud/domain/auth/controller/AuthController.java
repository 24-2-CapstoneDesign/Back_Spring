package com.potato.bookspud.domain.auth.controller;

import com.potato.bookspud.domain.auth.dto.response.LoginSuccessResponse;
import com.potato.bookspud.domain.auth.dto.response.SuccessResponse;
import com.potato.bookspud.domain.auth.service.KakaoSocialService;
import com.potato.bookspud.domain.user.dto.response.AccessTokenGetSuccess;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final KakaoSocialService kakaoSocialService;

    @PostMapping("/api/login/kakao")
    public ResponseEntity<SuccessResponse<AccessTokenGetSuccess>> login(
            @RequestParam final String authorizationCode
    ) {
        LoginSuccessResponse successResponse = kakaoSocialService.login(authorizationCode);

        AccessTokenGetSuccess tokenGetSuccess = new AccessTokenGetSuccess(
                successResponse.accessToken(),
                successResponse.refreshToken()
        );
        return ResponseEntity.status(HttpStatus.OK)
                .body(SuccessResponse.of("로그인 성공", tokenGetSuccess));
    }
}

