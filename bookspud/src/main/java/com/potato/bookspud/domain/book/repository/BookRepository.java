package com.potato.bookspud.domain.book.repository;

import com.potato.bookspud.domain.book.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

public interface BookRepository extends JpaRepository<Book, Long> {
}
