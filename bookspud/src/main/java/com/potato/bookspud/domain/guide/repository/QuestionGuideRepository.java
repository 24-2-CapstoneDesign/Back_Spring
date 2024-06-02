package com.potato.bookspud.domain.guide.repository;

import com.potato.bookspud.domain.guide.domain.QuestionGuide;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionGuideRepository extends JpaRepository<QuestionGuide, Long> {

}
