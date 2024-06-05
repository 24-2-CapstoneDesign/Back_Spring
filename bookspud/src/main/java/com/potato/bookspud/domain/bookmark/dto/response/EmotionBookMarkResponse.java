package com.potato.bookspud.domain.bookmark.dto.response;

import com.potato.bookspud.domain.book.domain.Book;
import com.potato.bookspud.domain.bookmark.domain.BookMark;
import lombok.Builder;

@Builder
public record EmotionBookMarkResponse(
        Long bookMarkId,
        String title,
        String author,
        String cover,
        String phase
) {
    public static EmotionBookMarkResponse of(BookMark bookMark, Book book) {
        return EmotionBookMarkResponse.builder()
                .bookMarkId(bookMark.getId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .cover(book.getCover())
                .phase(bookMark.getPhase())
                .build();
    }
}