package com.potato.bookspud.domain.bookreport.dto.request;

import lombok.Builder;

@Builder
public record DraftCreateRequest(
        String introQuestion,
        String introAnswer,
        String bodyQuestion,
        String bodyAnswer,
        String conclusionQuestion,
        String conclusionAnswer
) {
}
