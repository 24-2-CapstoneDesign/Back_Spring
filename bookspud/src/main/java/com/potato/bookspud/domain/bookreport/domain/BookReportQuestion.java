package com.potato.bookspud.domain.bookreport.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class BookReportQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private BookReport bookReport;

    String introQuestion;

    String introAnswer;

    String bodyQuestion;

    String bodyAnswer;

    String conclusionQuestion;

    String conclusionAnswer;
}
