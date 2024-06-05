package com.potato.bookspud.domain.bookreport.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public record QuestionsResponse(
        String introQuestion,
        String bodyQuestion,
        String conclusionQuestion
) {
    public static QuestionsResponse of (List<String> questions){
        return QuestionsResponse.builder()
                .introQuestion(questions.get(0))
                .bodyQuestion(questions.get(1))
                .conclusionQuestion(questions.get(2))
                .build();
    }
}
