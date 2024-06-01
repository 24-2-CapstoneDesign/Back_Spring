package com.potato.bookspud.domain.book.dto.response;

import com.potato.bookspud.domain.book.domain.MyBook;
import com.potato.bookspud.domain.common.TimeConverter;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Builder
public record BookListResponse(
        List<BookResponse> mybooks
) {
    public static BookListResponse of(List<BookResponse> mybooks){
        return BookListResponse.builder()
                .mybooks(mybooks)
                .build();
    }
}

