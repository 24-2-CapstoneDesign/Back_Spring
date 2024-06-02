package com.potato.bookspud.domain.guide.dto.response;

import com.potato.bookspud.domain.common.Emotion;
import com.potato.bookspud.domain.guide.domain.VocaGuide;
import lombok.Builder;

@Builder
public record VocaGuideResponse(
        Emotion emotion,
        String voca

) {
    public static VocaGuideResponse of (VocaGuide vocaGuide){
        return VocaGuideResponse.builder()
                .emotion(vocaGuide.getEmotion())
                .voca(vocaGuide.getVoca())
                .build();
    }
}
