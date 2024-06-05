package com.potato.bookspud.domain.bookreport.dto.request;

import com.potato.bookspud.domain.book.domain.MyBook;
import com.potato.bookspud.domain.bookmark.domain.BookMark;
import lombok.Builder;

import java.util.List;

@Builder
public record ArgumentsRequest(
        MyBook myBook,
        List<BookMark> bookMarks
) {
    public static ArgumentsRequest of (MyBook myBook, List<BookMark> bookMarks){
        return ArgumentsRequest.builder()
                .myBook(myBook)
                .bookMarks(bookMarks)
                .build();
    }

}
