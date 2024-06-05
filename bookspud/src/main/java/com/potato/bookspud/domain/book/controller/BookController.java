package com.potato.bookspud.domain.book.controller;

import com.potato.bookspud.domain.auth.annotation.AccessTokenUser;
import com.potato.bookspud.domain.book.dto.request.BookCreateRequest;
import com.potato.bookspud.domain.book.dto.request.BookUpdateRequest;
import com.potato.bookspud.domain.book.dto.response.*;
import com.potato.bookspud.domain.book.service.BookService;
import com.potato.bookspud.domain.common.BaseResponse;
import com.potato.bookspud.domain.user.domain.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.web.bind.annotation.*;

import static com.potato.bookspud.domain.common.SuccessCode.*;

@Tag(name = "Book Controller", description = "책 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class BookController {
    private final BookService bookService;
    @Operation(summary = "책 생성", description = "새로운 책을 등록하는 API")
    @PostMapping("/book")
    public BaseResponse<BookCreateResponse> createBook(@RequestBody BookCreateRequest request, @AccessTokenUser User user){
        val book = bookService.findOrCreateBook(request);
        val myBook = bookService.createMyBook(book, user);
        val result = BookCreateResponse.of(book.getId(), myBook.getId());
        return BaseResponse.success(CREATE_BOOK_SUCCESS, result);
    }

    @Operation(summary = "개별 책 조회", description = "추천 책에서 책의 상세 정보를 조회하는 API")
    @GetMapping("/book/{bookId}")
    public BaseResponse<BookDetailResponse> getBookDetail(@PathVariable Long bookId, @AccessTokenUser User user){
        val result = bookService.getMyBookById(bookId);
        return BaseResponse.success(READ_BOOK_SUCCESS, result);
    }

    @Operation(summary = "개별 책 삭제", description = "등록된 책 삭제 API")
    @DeleteMapping("/book/{id}")
    public BaseResponse deleteBook(@PathVariable Long id){
        bookService.deleteMyBookById(id);
        return BaseResponse.success(DELETE_BOOK_SUCCESS);
    }

    @Operation(summary = "개별 책 진행상황 업데이트", description = "현재 페이지, 전체 페이지 업데이트 API")
    @PatchMapping("/book/{id}")
    public BaseResponse patchBookProcess(@PathVariable Long id, @RequestBody BookUpdateRequest request){
        bookService.updateMyBookProcess(id, request);
        return BaseResponse.success(UPDATE_BOOK_PROCESS_SUCCESS);
    }

    @Operation(summary = "전체 책 조회", description = "전체 책 목록 조회 API")
    @GetMapping("/book")
    public BaseResponse<BooksResponse> getBookList(@AccessTokenUser User user){
        val result = bookService.getMyBooks(user);
        return BaseResponse.success(READ_BOOK_SUCCESS, result);
    }

    @Operation(summary = "특정 책을 가장 최근 읽은 사람의 다른 책 목록 조회", description = "북투북 추천 책 리스트 조회")
    @GetMapping("/recent/book")
    public BaseResponse<RecentBooksResponse> getBooksBybook(@AccessTokenUser User user){
        val result = bookService.getRecentBooksByBook(user);
        return BaseResponse.success(READ_RECOMMENDATION_BOOK_SUCCESS, result);
    }
}
