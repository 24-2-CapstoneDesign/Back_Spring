package com.potato.bookspud.domain.book.domain;

import com.potato.bookspud.domain.common.BaseEntity;
import com.potato.bookspud.domain.user.domain.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class MyBook extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bookId", nullable = false)
    private Book book;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    private Integer emotion;

    @Builder.Default
    private Boolean completed = false;

    private Integer finalPage;

    @Builder
    public MyBook(Book book, User user){
        this.book = book;
        this.user = user;
        this.completed = false;
    }
}
