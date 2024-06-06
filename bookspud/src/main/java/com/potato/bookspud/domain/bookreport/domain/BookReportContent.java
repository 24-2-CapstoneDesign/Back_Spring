package com.potato.bookspud.domain.bookreport.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class BookReportContent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bookReportId")
    BookReport bookReport;

    @Column(columnDefinition = "TEXT")
    String intro;

    @Column(columnDefinition = "TEXT")
    String introEmotion;

    @Column(columnDefinition = "TEXT")
    String body;

    @Column(columnDefinition = "TEXT")
    String bodyEmotion;

    @Column(columnDefinition = "TEXT")
    String conclusion;

    @Column(columnDefinition = "TEXT")
    String conclusionEmotion;

    @Builder
    public BookReportContent(BookReport bookReport, String intro, String body, String conclusion){
        this.bookReport = bookReport;
        this.intro = intro;
        this.body = body;
        this.conclusion = conclusion;
    }

    public void updateFinal(String introEmotion, String bodyEmotion, String conclusionEmotion){
        this.introEmotion = introEmotion;
        this.bodyEmotion = bodyEmotion;
        this.conclusionEmotion = conclusionEmotion;
    }
}
