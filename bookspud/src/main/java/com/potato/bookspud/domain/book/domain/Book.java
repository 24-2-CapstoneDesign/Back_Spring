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
    @Column(name = "bookId")
    private Long id;

    @Column(nullable = false)
    private String isbn;

    private String title;

    private String author;

    private String genre;

    private String cover;

    private Float price;

    @Column(columnDefinition = "TEXT")
    private String content;

    private Integer totalPage;

    private String purchaseLink;
}
