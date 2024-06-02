package com.potato.bookspud.domain.book.dto.response;

import com.potato.bookspud.domain.book.domain.MyBook;
import com.potato.bookspud.domain.common.TimeConverter;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record BookResponse(
        Long myBookId,
        String title,
        String author,
        String cover,
        String updatedAt,
        Integer totalPage,
        Integer finalPage
) {
    public static BookResponse of (MyBook mybook){
        return BookResponse.builder()
                .myBookId(mybook.getId())
                .title(mybook.getBook().getTitle())
                .author(mybook.getBook().getAuthor())
                .cover(mybook.getBook().getCover())
                .updatedAt(getUpdatedAtToString(mybook.getUpdatedAt()))
                .totalPage(mybook.getTotalPage())
                .finalPage(mybook.getFinalPage())
                .build();
    }

    private static String getUpdatedAtToString(LocalDateTime updatedAt){
        return TimeConverter.transferLocalDateTime(updatedAt);
    }

}
