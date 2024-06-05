package com.potato.bookspud.domain.bookmark.repository;

import com.potato.bookspud.domain.book.domain.Book;
import com.potato.bookspud.domain.book.domain.MyBook;
import com.potato.bookspud.domain.bookmark.domain.BookMark;
import com.potato.bookspud.domain.common.Emotion;
import com.potato.bookspud.domain.user.domain.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookMarkRepository extends JpaRepository<BookMark, Long> {

    List<BookMark> findByMyBookId(Long myBookId);

    List<BookMark> findByMyBook_Book_Id(Long bookId);

    long countByEmotionAndUser(Emotion emotion, User user);

    @Query("SELECT bm FROM BookMark bm WHERE bm.emotion = :emotion AND bm.user = :user ORDER BY function('RAND') ")
    List<BookMark> findRandomByEmotionAndUser(@Param("emotion") Emotion emotion, @Param("user") User user, Pageable pageable);

    List<BookMark> findByMyBookAndUser(MyBook mybook, User user);
}
