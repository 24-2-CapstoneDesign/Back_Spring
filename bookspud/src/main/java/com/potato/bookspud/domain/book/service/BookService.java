package com.potato.bookspud.domain.book.service;

import com.potato.bookspud.domain.book.domain.Book;
import com.potato.bookspud.domain.book.domain.MyBook;
import com.potato.bookspud.domain.book.dto.request.BookCreateRequest;
import com.potato.bookspud.domain.book.dto.request.BookUpdateRequest;
import com.potato.bookspud.domain.book.dto.response.*;
import com.potato.bookspud.domain.book.exception.RecentBookException;
import com.potato.bookspud.domain.book.repository.BookRepository;
import com.potato.bookspud.domain.book.repository.MyBookRepository;
import com.potato.bookspud.domain.common.Emotion;
import com.potato.bookspud.domain.user.domain.User;
import com.potato.bookspud.domain.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.potato.bookspud.domain.common.ErrorCode.NOT_FOUND_RECENT_BOOK_EXCEPTION;
import static com.potato.bookspud.domain.common.ErrorCode.NOT_FOUND_RECENT_USER_EXCEPTION;


@Service
@Builder
@RequiredArgsConstructor
@Transactional
public class BookService {
    private final BookRepository bookRepository;
    private final MyBookRepository myBookRepository;
    private final UserRepository userRepository;

    public Book findOrCreateBook(BookCreateRequest request) {
        return bookRepository.findByIsbn(request.isbn())
                .orElseGet(() -> {
                    Book newBook = Book.builder()
                            .isbn(request.isbn())
                            .title(request.title())
                            .author(request.author())
                            .cover(request.cover())
                            .price(request.price())
                            .salePrice(request.price())
                            .content(request.content())
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

    public BookDetailResponse getMyBookById(Long id){
        final Book book = bookRepository.getById(id);
        return BookDetailResponse.of(book);
    }

    public void deleteMyBookById(Long id){
        myBookRepository.deleteById(id);
    }

    public void updateMyBookProcess(Long id, BookUpdateRequest request){
        MyBook myBook = myBookRepository.getById(id);
        myBook.updateProcess(request.totalPage(), request.finalPage());
        myBookRepository.save(myBook);
    }

    public BooksResponse getMyBooks(User user){
        List<BookResponse> mybooks = myBookRepository.findAllByUser(user)
                .stream()
                .map(BookResponse::of)
                .toList();

        return BooksResponse.of(mybooks);
    }

    public RecentBooksResponse getRecentBooksByBook(User user){
        User recentUser = null;
        List<RecentBookResponse> recentBooksFromOtherUser = new ArrayList<>();

        // 사용자의 책 중 updatedAt이 가장 최근인 책 찾기
        MyBook recentBook = myBookRepository.findFirstByUserOrderByUpdatedAtDesc(user);

        // recentBook이 존재 -> Book to book
        if (recentBook != null){
            // 기준이 되는 책의 ID
            Long recentBookId = recentBook.getBook().getId();

            // 기준이 되는 책을 가진 다른 사용자 찾기
            List<MyBook> booksWithSameId = myBookRepository.findByBookIdAndUserNotOrderByUpdatedAtDesc(recentBookId, user);
            MyBook recentBookFromOtherUser = null;

            if(!booksWithSameId.isEmpty()){
                recentBookFromOtherUser = booksWithSameId.get(0);
                recentUser = recentBookFromOtherUser.getUser();

                // 해당 사용자의 책 중에서 최근 업데이트 된 5개의 책을 가져오기
                recentBooksFromOtherUser = myBookRepository.findTop5ByUserAndBookIdNotOrderByUpdatedAtDesc(recentUser, recentBookId)
                        .stream()
                        .map(RecentBookResponse::of)
                        .toList();
            }
        }

        // recentUser를 찾지 못한 경우, recentBook을 찾지 못한 경우 -> book to user
        if (recentUser == null){
            // 현재 user의 birthyear와 같은 다른 user 중 가장 최근 접속한 user 1명 찾기
            recentUser = userRepository.findTopByBirthyearAndIdNotOrderByUpdatedAtDesc(user.getBirthyear(), user.getId());
            if (recentUser == null){
                throw new RecentBookException(NOT_FOUND_RECENT_USER_EXCEPTION);
            }
            // 해당 사용자의 책 중에서 최근 업데이트 된 5개의 책을 가져오기
            recentBooksFromOtherUser = myBookRepository.findTop5ByUserOrderByUpdatedAtDesc(recentUser)
                    .stream()
                    .map(RecentBookResponse::of)
                    .toList();
        }

        return RecentBooksResponse.of(recentBooksFromOtherUser);
    }

    public MyBook getMyBookByMybookId(Long id){
        return myBookRepository.getById(id);
    }
}
