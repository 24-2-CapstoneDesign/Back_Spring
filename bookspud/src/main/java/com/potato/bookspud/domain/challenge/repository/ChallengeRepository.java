package com.potato.bookspud.domain.challenge.repository;

import com.potato.bookspud.domain.challenge.domain.Challenge;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChallengeRepository  extends JpaRepository<Challenge, Long> {
}
