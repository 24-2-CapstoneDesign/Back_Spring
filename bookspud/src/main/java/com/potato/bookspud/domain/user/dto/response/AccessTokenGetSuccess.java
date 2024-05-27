package com.potato.bookspud.domain.user.dto.response;

public record AccessTokenGetSuccess(
        String accessToken,
        String refreshToken
) {
    public static AccessTokenGetSuccess of(
            final String accessToken,
            final String refreshToken
    ) {
        return new AccessTokenGetSuccess(accessToken, refreshToken);
    }
}
