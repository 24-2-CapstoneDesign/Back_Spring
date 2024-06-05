package com.potato.bookspud.domain.bookreport.repository;

import com.potato.bookspud.domain.bookreport.domain.BookReport;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BookReportRepository extends JpaRepository<BookReport, Long> {
    BookReport getById(Long id);

}
