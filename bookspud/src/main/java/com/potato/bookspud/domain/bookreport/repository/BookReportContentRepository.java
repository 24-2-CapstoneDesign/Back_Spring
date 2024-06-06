package com.potato.bookspud.domain.bookreport.repository;

import com.potato.bookspud.domain.bookreport.domain.BookReport;
import com.potato.bookspud.domain.bookreport.domain.BookReportContent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookReportContentRepository extends JpaRepository<BookReportContent, Long> {
    BookReportContent findByBookReport(BookReport bookReport);
}
