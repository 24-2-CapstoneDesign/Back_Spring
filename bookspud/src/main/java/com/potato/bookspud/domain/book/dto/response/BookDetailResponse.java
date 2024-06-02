package com.potato.bookspud.domain.book.dto.response;

import com.potato.bookspud.domain.book.domain.Book;

import lombok.Builder;


@Builder
public record BookDetailResponse(
        String title,
        String author,
        String cover,
        Float price,
        Float salePrice,
        String content,
        String purchaseLink
){
    public static BookDetailResponse of (Book book){
        return BookDetailResponse.builder()
                .title(book.getTitle())
                .author(book.getAuthor())
                .cover(book.getCover())
                .price(book.getPrice())
                .salePrice(book.getSalePrice())
                .content(book.getContent())
                .purchaseLink(book.getPurchaseLink())
                .build();
    }
}
