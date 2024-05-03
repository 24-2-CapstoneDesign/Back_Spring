package com.potato.bookspud.domain.bookmark.repository;

import com.potato.bookspud.domain.bookmark.domain.BookMark;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookMarkRepository extends JpaRepository<BookMark, Long> {
}
