package com.potato.bookspud.domain.book.controller;

import com.potato.bookspud.domain.auth.annotation.AccessTokenUser;
import com.potato.bookspud.domain.book.dto.request.BookCreateRequest;
import com.potato.bookspud.domain.book.dto.response.BookCreateResponse;
import com.potato.bookspud.domain.book.service.BookService;
import com.potato.bookspud.domain.common.BaseResponse;
import com.potato.bookspud.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.web.bind.annotation.*;

import static com.potato.bookspud.domain.common.SuccessCode.CREATE_BOOK_SUCCESS;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class BookController {
    private final BookService bookService;
    @PostMapping("/book")
    public BaseResponse<BookCreateResponse> createBook(@RequestBody BookCreateRequest request, @AccessTokenUser User user){
        val book = bookService.findOrCreateBook(request);
        val myBook = bookService.createMyBook(book, user);
        val response = BookCreateResponse.of(book.getId(), myBook.getId());
        return BaseResponse.success(CREATE_BOOK_SUCCESS, response);
    }


}
