package com.potato.bookspud.domain.bookreport.dto.response;

import com.potato.bookspud.domain.bookreport.domain.BookReportContent;
import lombok.Builder;

@Builder
public record DraftResponse(
        String intro,
        String body,
        String conclusion
) {
    public static DraftResponse of (BookReportContent bookReportContent){
        return DraftResponse.builder()
                .intro(bookReportContent.getIntro())
                .body(bookReportContent.getBody())
                .conclusion(bookReportContent.getConclusion())
                .build();
    }
}
