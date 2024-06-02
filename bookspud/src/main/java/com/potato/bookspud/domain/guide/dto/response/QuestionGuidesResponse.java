package com.potato.bookspud.domain.guide.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public record QuestionGuidesResponse(
        List<QuestionGuideResponse> questionGuides
) {
    public static QuestionGuidesResponse of (List<QuestionGuideResponse> questionGuides){
        return QuestionGuidesResponse.builder()
                .questionGuides(questionGuides)
                .build();
    }
}
