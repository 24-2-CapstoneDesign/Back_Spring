package com.potato.bookspud.domain.book.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
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

    private String genre;

    private String cover;

    private Float price;

    @Column(columnDefinition = "TEXT")
    private String content;

    private Integer totalPage;

    private String purchaseLink;
}
