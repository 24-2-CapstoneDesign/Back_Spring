package com.potato.bookspud.domain.user.repository;

import com.potato.bookspud.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    User findById(long id);
    Optional<User> findBySocialId(Long socialId);
}
