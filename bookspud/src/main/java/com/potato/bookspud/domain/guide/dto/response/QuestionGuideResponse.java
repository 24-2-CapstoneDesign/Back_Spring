package com.potato.bookspud.domain.guide.dto.response;

import com.potato.bookspud.domain.guide.domain.QuestionGuide;
import lombok.Builder;

@Builder
public record QuestionGuideResponse(
        String question
) {
    public static QuestionGuideResponse of (QuestionGuide questionGuide){
        return QuestionGuideResponse.builder()
                .question(questionGuide.getQuestion())
                .build();
    }
}
