package com.potato.bookspud.domain.book.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long id;

    @NonNull
    @Column(unique = true)
    private String isbn;

    private String title;

    private String author;

    private String cover;

    private Float price;

    private Float salePrice;

    @Column(columnDefinition = "TEXT")
    private String content;

    private String purchaseLink;
}
