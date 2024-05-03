package com.potato.bookspud.domain.user.repository;

import com.potato.bookspud.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
