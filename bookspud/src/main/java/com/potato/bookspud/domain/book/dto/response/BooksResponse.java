package com.potato.bookspud.domain.book.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public record BooksResponse(
        List<BookResponse> myBooks
) {
    public static BooksResponse of(List<BookResponse> myBooks){
        return BooksResponse.builder()
                .myBooks(myBooks)
                .build();
    }
}

