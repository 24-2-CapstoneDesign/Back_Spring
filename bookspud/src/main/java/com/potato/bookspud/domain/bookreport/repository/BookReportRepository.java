package com.potato.bookspud.domain.bookreport.repository;

import com.potato.bookspud.domain.bookreport.domain.BookReport;
import com.potato.bookspud.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface BookReportRepository extends JpaRepository<BookReport, Long> {
    BookReport getById(Long id);
    List<BookReport> findAllByUser(User user);

}
