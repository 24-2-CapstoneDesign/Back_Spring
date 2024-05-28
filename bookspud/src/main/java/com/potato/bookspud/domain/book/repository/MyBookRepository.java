package com.potato.bookspud.domain.book.repository;

import com.potato.bookspud.domain.book.domain.MyBook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyBookRepository extends JpaRepository<MyBook, Long> {
}
