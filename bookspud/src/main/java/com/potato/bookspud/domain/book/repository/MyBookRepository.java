package com.potato.bookspud.domain.book.repository;

import com.potato.bookspud.domain.book.domain.MyBook;
import com.potato.bookspud.domain.common.Emotion;
import com.potato.bookspud.domain.user.domain.User;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MyBookRepository extends JpaRepository<MyBook, Long> {
    MyBook getById(Long id);
    void deleteById(Long id);
    List<MyBook> findAllByUser(User user);

    MyBook findFirstByUserOrderByUpdatedAtDesc(User user);
    List<MyBook> findByBookIdAndUserNotOrderByUpdatedAtDesc(Long bookId, User user);
    @Query("SELECT b FROM MyBook b WHERE b.user = :user AND b.book.id <> :bookId ORDER BY b.updatedAt DESC")
    List<MyBook> findTop5ByUserAndBookIdNotOrderByUpdatedAtDesc(@Param("user") User user, @Param("bookId") Long bookId);

    List<MyBook> findByEmotion(Emotion emotion);
}
