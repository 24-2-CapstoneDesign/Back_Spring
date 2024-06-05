package com.potato.bookspud.domain.bookreport.domain;

import com.potato.bookspud.domain.book.domain.MyBook;
import com.potato.bookspud.domain.common.BaseEntity;
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

    String argument;
    @Column(columnDefinition = "TEXT")
    String draft;

    Boolean completed;

    @Column(columnDefinition = "TEXT")
    String finalReport;

    @Builder
    public BookReport (MyBook mybook, String argument){
        this.mybook = mybook;
        this.argument = argument;
    }
}
