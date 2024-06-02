package com.potato.bookspud.domain.book.dto.request;

import lombok.Builder;

@Builder
public record BookUpdateRequest(
        Integer totalPage,
        Integer finalPage
) {
}
