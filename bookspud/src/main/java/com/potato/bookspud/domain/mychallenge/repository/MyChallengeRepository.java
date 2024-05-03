package com.potato.bookspud.domain.mychallenge.repository;

import com.potato.bookspud.domain.mychallenge.domain.MyChallenge;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyChallengeRepository extends JpaRepository<MyChallenge, Long> {
}
