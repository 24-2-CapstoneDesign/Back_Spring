package com.potato.bookspud.domain.bookreport.dto.request;

import lombok.Builder;

@Builder
public record FinalCreateRequest(
        String finalReport
) {
}
