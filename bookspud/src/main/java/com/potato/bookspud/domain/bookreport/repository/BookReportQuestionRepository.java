package com.potato.bookspud.domain.bookreport.repository;

import com.potato.bookspud.domain.bookreport.domain.BookReport;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BookReportQuestionRepository extends JpaRepository<BookReport, Long>{
}
