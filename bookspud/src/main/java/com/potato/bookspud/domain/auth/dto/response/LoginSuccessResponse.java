package com.potato.bookspud.domain.auth.dto.response;

public record LoginSuccessResponse(
        String accessToken,
        String refreshToken
) {
    public static LoginSuccessResponse of(
            final String accessToken,
            final String refreshToken
    ) {
        return new LoginSuccessResponse(accessToken, refreshToken);
    }
}