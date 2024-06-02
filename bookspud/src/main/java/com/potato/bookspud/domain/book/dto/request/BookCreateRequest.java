package com.potato.bookspud.domain.book.dto.request;

import lombok.Builder;

@Builder
public record BookCreateRequest (
        String isbn,
        String title,
        String author,
        String cover,
        Float price,
        Float salePrice,
        String content,
        String purchaseLink
){
}

