package com.potato.bookspud.domain.auth.client;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record KakaoUserProfile(
        String nickname,
        String profileImageUrl,
        String accountEmail
) {
}
