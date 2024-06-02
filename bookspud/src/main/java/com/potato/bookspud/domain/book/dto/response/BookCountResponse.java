package com.potato.bookspud.domain.book.dto.response;

import com.potato.bookspud.domain.common.Emotion;
import lombok.Builder;

@Builder
public record BookCountResponse(
        Emotion emotion,
        long count
) {
    public static BookCountResponse of (Emotion emotion, long count){
        return BookCountResponse.builder()
                .emotion(emotion)
                .count(count)
                .build();
    }
}
