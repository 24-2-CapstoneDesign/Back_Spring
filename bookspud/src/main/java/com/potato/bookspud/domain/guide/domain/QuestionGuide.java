package com.potato.bookspud.domain.guide.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class QuestionGuide {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
