package com.potato.bookspud.domain.book.dto.request;
import lombok.NonNull;
public record BookCreateRequest(
        @NonNull
        String isbn,
        String title,
        String author,
        String genre,
        String cover,
        Float price,
        String content,
        Integer totalPage,
        String purchaseLink
) {
}