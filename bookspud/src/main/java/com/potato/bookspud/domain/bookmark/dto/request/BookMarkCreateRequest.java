package com.potato.bookspud.domain.bookmark.dto.request;

import com.potato.bookspud.domain.common.Emotion;

public record BookMarkCreateRequest(
        Long myBookId,
        String phase,
        Integer page,
        String memo,
        Emotion emotion
) {
}
