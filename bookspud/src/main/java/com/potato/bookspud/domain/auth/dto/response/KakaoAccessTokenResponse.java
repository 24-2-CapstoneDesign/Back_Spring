package com.potato.bookspud.domain.auth.dto.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record KakaoAccessTokenResponse(
        String accessToken
) {
    public static KakaoAccessTokenResponse of(
            final String accessToken
    ) {
        return new KakaoAccessTokenResponse(
                accessToken
        );
    }
}
