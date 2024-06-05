package com.potato.bookspud.domain.bookreport.dto.response;

import com.potato.bookspud.domain.bookreport.domain.BookReport;
import lombok.Builder;

@Builder
public record ArgumentCreateResponse(
        Long id
) {
    public static ArgumentCreateResponse of (BookReport bookReport){
        return ArgumentCreateResponse.builder()
                .id(bookReport.getId())
                .build();
    }
}
