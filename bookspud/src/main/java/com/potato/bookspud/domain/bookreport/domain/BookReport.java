package com.potato.bookspud.domain.bookreport.domain;

import com.potato.bookspud.domain.book.domain.MyBook;
import com.potato.bookspud.domain.common.BaseEntity;
import com.potato.bookspud.domain.user.domain.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class BookReport extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mybookId", nullable = false)
    private MyBook mybook;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="userId", nullable = false)
    private User user;

    String argument;

    @Enumerated(value = EnumType.STRING)
    Status status;

    @Builder
    public BookReport (MyBook mybook, User user, String argument){
        this.mybook = mybook;
        this.user = user;
        this.argument = argument;
        this.status = Status.BLANK;
    }

    public void updateStatus(Status status){
        this.status = status;
    }

}
