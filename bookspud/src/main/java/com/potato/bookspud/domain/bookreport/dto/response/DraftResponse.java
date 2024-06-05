package com.potato.bookspud.domain.bookreport.dto.response;

import com.potato.bookspud.domain.bookreport.domain.BookReport;
import lombok.Builder;

@Builder
public record DraftResponse(
        String draft
) {
    public static DraftResponse of (BookReport bookReport){
        return DraftResponse.builder()
                .draft(bookReport.getDraft())
                .build();
    }
}
