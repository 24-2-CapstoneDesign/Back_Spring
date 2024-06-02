package com.potato.bookspud.domain.book.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public record RecentBooksResponse(
        List<RecentBookResponse> myBooks
) {
    public static RecentBooksResponse of (List<RecentBookResponse> myBooks){
        return RecentBooksResponse.builder()
                .myBooks(myBooks)
                .build();
    }
}
