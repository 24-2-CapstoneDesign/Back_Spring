package com.potato.bookspud.domain.book.service;

import com.potato.bookspud.domain.book.domain.Book;
import com.potato.bookspud.domain.book.domain.MyBook;
import com.potato.bookspud.domain.book.dto.request.BookCreateRequest;
import com.potato.bookspud.domain.book.repository.BookRepository;
import com.potato.bookspud.domain.book.repository.MyBookRepository;
import com.potato.bookspud.domain.user.domain.User;
import jakarta.transaction.Transactional;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Builder
@RequiredArgsConstructor
@Transactional
public class BookService {
    private final BookRepository bookRepository;
    private final MyBookRepository myBookRepository;

    public Book findOrCreateBook(BookCreateRequest request) {
        return bookRepository.findByIsbn(request.isbn())
                .orElseGet(() -> {
                    Book newBook = Book.builder()
                            .isbn(request.isbn())
                            .title(request.title())
                            .author(request.author())
                            .genre(request.genre())
                            .cover(request.cover())
                            .price(request.price())
                            .content(request.content())
                            .totalPage(request.totalPage())
                            .purchaseLink(request.purchaseLink())
                            .build();
                    return bookRepository.save(newBook);
                });
    }

    public MyBook createMyBook(Book book, User user){
        MyBook newMyBook = MyBook.builder()
                .book(book)
                .user(user)
                .build();
        return myBookRepository.save(newMyBook);
    }
}
