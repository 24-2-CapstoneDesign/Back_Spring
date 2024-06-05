package com.potato.bookspud.domain.book.domain;

import com.potato.bookspud.domain.common.BaseEntity;
import com.potato.bookspud.domain.common.Emotion;
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
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bookId", nullable = false)
    private Book book;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @Enumerated(value = EnumType.STRING)
    private Emotion emotion;

    private Integer totalPage;

    private Integer finalPage;

    private Boolean completed;

    @Builder
    public MyBook(Book book, User user){
        this.book = book;
        this.user = user;
        this.finalPage = 0;
        this.totalPage = 0;
        this.completed = false;
    }

    public void updateProcess(Integer totalPage, Integer finalPage){
        this.totalPage = totalPage;
        this.finalPage = finalPage;
    }
}
