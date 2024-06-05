package com.potato.bookspud.domain.bookreport.dto.request;

import lombok.Builder;

@Builder
public record AnswersRequest(
        String introAnswer,
        String bodyAnswer,
        String conclusionAnswer
) {
}
