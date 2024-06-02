package com.potato.bookspud.domain.book.dto.request;

import lombok.Builder;

@Builder
public record BookCreateRequest (
        String isbn,
        String title,
        String author,
        String genre,
        String cover,
        Float price,
        String content,
        Integer totalPage,
        String purchaseLink
){
}

