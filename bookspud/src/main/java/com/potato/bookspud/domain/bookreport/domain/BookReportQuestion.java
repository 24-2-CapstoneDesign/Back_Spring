package com.potato.bookspud.domain.bookreport.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class BookReportQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_report_id")
    private BookReport bookReport;

    String introQuestion;

    String introAnswer;

    String bodyQuestion;

    String bodyAnswer;

    String conclusionQuestion;

    String conclusionAnswer;

    @Builder
    public BookReportQuestion(BookReport bookReport, String introQuestion, String bodyQuestion, String conclusionQuestion){
        this.bookReport = bookReport;
        this.introQuestion = introQuestion;
        this.bodyQuestion = bodyQuestion;
        this.conclusionQuestion = conclusionQuestion;
    }

    public void updateAnswers(String introAnswer, String bodyAnswer, String conclusionAnswer){
        this.introAnswer = introAnswer;
        this.bodyAnswer = bodyAnswer;
        this.conclusionAnswer = conclusionAnswer;
    }
}
