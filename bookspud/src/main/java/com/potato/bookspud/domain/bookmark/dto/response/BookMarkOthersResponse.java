package com.potato.bookspud.domain.bookmark.dto.response;

import com.potato.bookspud.domain.common.Emotion;

public record BookMarkOthersResponse(
        String nickName,
        String profileImageUrl,
        Emotion emotion,
        String phase,
        String memo
) {
}

