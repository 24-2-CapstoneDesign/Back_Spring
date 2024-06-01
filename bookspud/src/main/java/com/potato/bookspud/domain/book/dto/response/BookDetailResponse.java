package com.potato.bookspud.domain.book.dto.response;

import com.potato.bookspud.domain.book.domain.MyBook;
import lombok.Builder;

import static lombok.AccessLevel.PRIVATE;

@Builder(access = PRIVATE)
public record BookGetResponse (
        Long mybookId,
        String title,
        String author,
        String genre,
        String cover,
        Integer emotion,
        Integer finalPage,
        Boolean completed
){
    public static BookGetResponse of (MyBook mybook){
        return BookGetResponse.builder()
                .mybookId(mybook.getId())
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
