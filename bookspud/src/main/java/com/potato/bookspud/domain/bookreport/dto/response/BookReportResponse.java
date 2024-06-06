package com.potato.bookspud.domain.bookreport.dto.response;

import com.potato.bookspud.domain.book.domain.MyBook;
import com.potato.bookspud.domain.bookreport.domain.BookReport;
import com.potato.bookspud.domain.bookreport.domain.Status;
import com.potato.bookspud.domain.common.TimeConverter;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record BookReportResponse(
        Long bookReportId,
        String cover,
        String title,
        String updatedAt,
        String argument,
        Status status
) {
    public static BookReportResponse of (BookReport bookReport){
        return BookReportResponse.builder()
                .bookReportId(bookReport.getId())
                .cover(bookReport.getMybook().getBook().getCover())
                .title(bookReport.getMybook().getBook().getTitle())
                .updatedAt(getUpdatedAtToString(bookReport.getMybook().getUpdatedAt()))
                .argument(bookReport.getArgument())
                .status(bookReport.getStatus())
                .build();
    }

    private static String getUpdatedAtToString(LocalDateTime updatedAt){
        return TimeConverter.transferLocalDateTime(updatedAt);
    }
}
