package com.potato.bookspud.domain.bookmark.dto.response;

import lombok.Builder;

@Builder
public record BookMarkCreateResponse(
        Long bookMarkId
) {
    public static BookMarkCreateResponse of (Long bookMarkId) {
        return BookMarkCreateResponse.builder()
                .bookMarkId(bookMarkId)
                .build();
    }
}
