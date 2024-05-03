package com.potato.bookspud.domain.book.repository;

import com.potato.bookspud.domain.book.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
