package com.potato.bookspud.domain.mybook.repository;

import com.potato.bookspud.domain.mybook.domain.MyBook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyBookRepository extends JpaRepository<MyBook, Long> {
}
