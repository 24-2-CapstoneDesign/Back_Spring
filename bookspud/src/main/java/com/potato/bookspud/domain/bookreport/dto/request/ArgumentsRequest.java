package com.potato.bookspud.domain.chatgpt.dto.request;

import com.potato.bookspud.domain.book.domain.Book;
import com.potato.bookspud.domain.bookmark.domain.BookMark;

import java.util.List;

public record ArgumentsRequest(
        Book book,
        List<BookMark> bookMarks
) {
}
