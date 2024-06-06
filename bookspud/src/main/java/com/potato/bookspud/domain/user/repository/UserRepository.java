package com.potato.bookspud.domain.user.repository;

import com.potato.bookspud.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    User findById(long id);
    Optional<User> findBySocialId(Long socialId);
    @Query("SELECT u FROM User u WHERE u.birthyear = :birthyear AND u.id <> :userId ORDER BY u.updatedAt DESC")
    User findTopByBirthyearAndIdNotOrderByUpdatedAtDesc(@Param("birthyear") String birthyear, @Param("userId") Long userId);
}
