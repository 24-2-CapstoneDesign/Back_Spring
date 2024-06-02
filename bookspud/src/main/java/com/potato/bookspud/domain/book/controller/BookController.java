package com.potato.bookspud.domain.book.controller;

import com.potato.bookspud.domain.auth.annotation.AccessTokenUser;
import com.potato.bookspud.domain.book.dto.request.BookCreateRequest;
import com.potato.bookspud.domain.book.dto.response.*;
import com.potato.bookspud.domain.book.service.BookService;
import com.potato.bookspud.domain.common.BaseResponse;
import com.potato.bookspud.domain.common.Emotion;
import com.potato.bookspud.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.web.bind.annotation.*;

import static com.potato.bookspud.domain.common.SuccessCode.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class BookController {
    private final BookService bookService;
    @PostMapping("/book")
    public BaseResponse<BookCreateResponse> createBook(@RequestBody BookCreateRequest request, @AccessTokenUser User user){
        val book = bookService.findOrCreateBook(request);
        val myBook = bookService.createMyBook(book, user);
        val result = BookCreateResponse.of(book.getId(), myBook.getId());
        return BaseResponse.success(CREATE_BOOK_SUCCESS, result);
    }

    @GetMapping("/book/{id}")
    public BaseResponse<BookDetailResponse> getBookDetail(@PathVariable Long id, @AccessTokenUser User user){
        val result = bookService.getMyBookById(id);
        return BaseResponse.success(READ_BOOK_SUCCESS, result);
    }

    @DeleteMapping("/book/{id}")
    public BaseResponse deleteBook(@PathVariable Long id){
        bookService.deleteMyBookById(id);
        return BaseResponse.success(DELETE_BOOK_SUCCESS);
    }

    @GetMapping("/book/{emotion}/list")
    public BaseResponse<BooksResponse> getBooksByEmotion(@PathVariable Emotion emotion, @AccessTokenUser User user){
        val result = bookService.getMyBooksByEmotion(emotion, user);
        return BaseResponse.success(READ_BOOK_SUCCESS, result);
    }

    @GetMapping("/book/{emotion}/count")
    public BaseResponse<BookCountResponse> getBookCountsByEmotion(@PathVariable Emotion emotion, @AccessTokenUser User user){
        val result = bookService.getMyBookCountsByEmotion(emotion, user);
        return BaseResponse.success(READ_BOOK_COUNT_SUCCESS, result);
    }

    @GetMapping("/recent/book")
    public BaseResponse<RecentBooksResponse> getBooksBybook(@AccessTokenUser User user){
        val result = bookService.getRecentBooksByBook(user);
        return BaseResponse.success(READ_RECOMMENDATION_BOOK_SUCCESS, result);
    }
}
