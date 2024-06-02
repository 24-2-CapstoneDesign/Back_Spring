package com.potato.bookspud.domain.book.dto.response;

import com.potato.bookspud.domain.book.domain.MyBook;
import lombok.Builder;

@Builder
public record RecentBookResponse(
        Long bookId,
        String title,
        String author,
        String cover
) {
    public static RecentBookResponse of (MyBook mybook){
        return RecentBookResponse.builder()
                .bookId(mybook.getBook().getId())
                .title(mybook.getBook().getTitle())
                .author(mybook.getBook().getAuthor())
                .cover(mybook.getBook().getCover())
                .build();
    }
}
