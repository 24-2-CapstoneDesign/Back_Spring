package com.potato.bookspud.domain.guide.repository;

import com.potato.bookspud.domain.guide.domain.VocaGuide;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VocaGuideRepository extends JpaRepository<VocaGuide, Long> {
}
