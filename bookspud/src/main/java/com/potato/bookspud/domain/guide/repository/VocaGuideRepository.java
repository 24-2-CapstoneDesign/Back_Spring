package com.potato.bookspud.domain.guide.repository;

import com.potato.bookspud.domain.common.Emotion;
import com.potato.bookspud.domain.guide.domain.VocaGuide;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VocaGuideRepository extends JpaRepository<VocaGuide, Long> {
    List<VocaGuide> findAllByEmotion(Emotion emotion);
}
