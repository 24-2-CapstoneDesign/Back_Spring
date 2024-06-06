package com.potato.bookspud.domain.bookreport.dto.response;

import com.potato.bookspud.domain.bookreport.domain.BookReportContent;
import lombok.Builder;

@Builder
public record BookReportDetailResponse(
        String intro,
        String introEmotion,
        String body,
        String bodyEmotion,
        String conclusion,
        String conclusionEmotion
) {
    public static BookReportDetailResponse of (BookReportContent bookReportContent){
        return BookReportDetailResponse.builder()
                .intro(bookReportContent.getIntro())
                .introEmotion(bookReportContent.getIntroEmotion())
                .body(bookReportContent.getBody())
                .bodyEmotion(bookReportContent.getBodyEmotion())
                .conclusion(bookReportContent.getConclusion())
                .conclusionEmotion(bookReportContent.getConclusionEmotion())
                .build();
    }
}
