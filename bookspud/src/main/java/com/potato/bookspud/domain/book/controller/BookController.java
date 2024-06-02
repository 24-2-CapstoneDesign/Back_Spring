package com.potato.bookspud.domain.book.controller;

import com.potato.bookspud.domain.auth.annotation.AccessTokenUser;
import com.potato.bookspud.domain.book.dto.request.BookCreateRequest;
import com.potato.bookspud.domain.book.dto.request.BookUpdateRequest;
import com.potato.bookspud.domain.book.dto.response.*;
import com.potato.bookspud.domain.book.service.BookService;
import com.potato.bookspud.domain.common.BaseResponse;
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

    @GetMapping("/book/{bookId}")
    public BaseResponse<BookDetailResponse> getBookDetail(@PathVariable Long bookId, @AccessTokenUser User user){
        val result = bookService.getMyBookById(bookId);
        return BaseResponse.success(READ_BOOK_SUCCESS, result);
    }

    @DeleteMapping("/book/{id}")
    public BaseResponse deleteBook(@PathVariable Long id){
        bookService.deleteMyBookById(id);
        return BaseResponse.success(DELETE_BOOK_SUCCESS);
    }

    @PatchMapping("/book/{id}")
    public BaseResponse patchBookProcess(@PathVariable Long id, @RequestBody BookUpdateRequest request){
        bookService.updateMyBookProcess(id, request);
        return BaseResponse.success(UPDATE_BOOK_PROCESS_SUCCESS);
    }

    @GetMapping("/book")
    public BaseResponse<BooksResponse> getBookList(@AccessTokenUser User user){
        val result = bookService.getMyBooks(user);
        return BaseResponse.success(READ_BOOK_SUCCESS, result);
    }

    @GetMapping("/recent/book")
    public BaseResponse<RecentBooksResponse> getBooksBybook(@AccessTokenUser User user){
        val result = bookService.getRecentBooksByBook(user);
        return BaseResponse.success(READ_RECOMMENDATION_BOOK_SUCCESS, result);
    }
}
