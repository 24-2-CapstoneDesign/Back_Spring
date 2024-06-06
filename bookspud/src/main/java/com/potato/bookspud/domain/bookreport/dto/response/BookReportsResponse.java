package com.potato.bookspud.domain.bookreport.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public record BookReportsResponse(
        List<BookReportResponse> bookReportResponses
) {
    public static BookReportsResponse of (List<BookReportResponse> bookReportResponses){
        return BookReportsResponse.builder()
                .bookReportResponses(bookReportResponses)
                .build();
    }
}
