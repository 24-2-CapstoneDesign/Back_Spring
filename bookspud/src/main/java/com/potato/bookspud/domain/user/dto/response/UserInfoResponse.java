package com.potato.bookspud.domain.user.dto.response;

public record UserInfoResponse(
        Long socialId,
        String email
) {
    public static UserInfoResponse of(
            final Long socialId,
            final String email
    ) {
        return new UserInfoResponse(socialId, email);
    }
}
