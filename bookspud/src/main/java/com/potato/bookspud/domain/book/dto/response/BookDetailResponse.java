package com.potato.bookspud.domain.book.dto.response;

import com.potato.bookspud.domain.book.domain.MyBook;
import com.potato.bookspud.domain.common.Emotion;
import lombok.Builder;

import static lombok.AccessLevel.PRIVATE;

@Builder
public record BookDetailResponse(
        String title,
        String author,
        String genre,
        String cover,
        Emotion emotion,
        Integer finalPage,
        Boolean completed
){
    public static BookDetailResponse of (MyBook mybook){
        return BookDetailResponse.builder()
                .title(mybook.getBook().getTitle())
                .author(mybook.getBook().getAuthor())
                .genre(mybook.getBook().getGenre())
                .cover(mybook.getBook().getCover())
                .emotion(mybook.getEmotion())
                .finalPage(mybook.getFinalPage())
                .completed(mybook.getCompleted())
                .build();
    }
}
