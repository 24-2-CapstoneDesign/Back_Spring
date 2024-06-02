package com.potato.bookspud.domain.book.repository;

import com.potato.bookspud.domain.book.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByIsbn(String isbn);
    Book getById(Long id);
}
