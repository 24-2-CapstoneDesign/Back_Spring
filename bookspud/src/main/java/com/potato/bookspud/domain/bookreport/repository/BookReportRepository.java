package com.potato.bookspud.domain.bookreport.repository;

import com.potato.bookspud.domain.bookreport.domain.BookReportQuestion;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BookReportRepository extends JpaRepository<BookReportQuestion, Long> {

}
