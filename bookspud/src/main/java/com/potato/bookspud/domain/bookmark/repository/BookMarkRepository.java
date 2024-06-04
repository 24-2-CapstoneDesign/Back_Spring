package com.potato.bookspud.domain.bookmark.repository;

import com.potato.bookspud.domain.bookmark.domain.BookMark;
import com.potato.bookspud.domain.common.Emotion;
import com.potato.bookspud.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookMarkRepository extends JpaRepository<BookMark, Long> {

    List<BookMark> findByMyBookId(Long myBookId);

    List<BookMark> findByMyBook_Book_Id(Long bookId);

    long countByEmotionAndUser(Emotion emotion, User user);
}
