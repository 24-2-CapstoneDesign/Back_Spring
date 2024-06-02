package com.potato.bookspud.domain.book.dto.response;

import lombok.Builder;

@Builder
public record BookCreateResponse(
        Long bookId,
        Long myBookId
) {
    public static BookCreateResponse of (Long bookId, Long myBookId){
        return BookCreateResponse.builder()
                .bookId(bookId)
                .myBookId(myBookId)
                .build();
    }

}
