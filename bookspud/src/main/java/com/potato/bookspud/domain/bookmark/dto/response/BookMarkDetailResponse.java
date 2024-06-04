package com.potato.bookspud.domain.bookmark.dto.response;

import com.potato.bookspud.domain.bookmark.domain.BookMark;
import com.potato.bookspud.domain.common.Emotion;
import lombok.Builder;

@Builder
public record BookMarkDetailResponse(
        Long bookMarkId,
        String phase,
        Integer page,
        String memo,
        Emotion emotion
) {
    public static BookMarkDetailResponse of (BookMark bookMark) {
        return BookMarkDetailResponse.builder()
                .bookMarkId(bookMark.getId())
                .phase(bookMark.getPhase())
                .page(bookMark.getPage())
                .memo(bookMark.getMemo())
                .emotion(bookMark.getEmotion())
                .build();
    }
}
