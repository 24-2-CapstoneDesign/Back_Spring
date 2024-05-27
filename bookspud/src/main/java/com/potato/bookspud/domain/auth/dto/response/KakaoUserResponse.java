package com.potato.bookspud.domain.auth.dto.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.potato.bookspud.domain.auth.client.KakaoAccount;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record KakaoUserResponse(
        Long id,
        KakaoAccount kakaoAccount
) {
}
