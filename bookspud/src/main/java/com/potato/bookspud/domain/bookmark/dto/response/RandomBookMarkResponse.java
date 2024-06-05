package com.potato.bookspud.domain.bookmark.dto.response;

import com.potato.bookspud.domain.common.Emotion;

public record RandomBookMarkResponse(
        Emotion emotion,
        String phase,
        String title,
        String cover
) {
}
