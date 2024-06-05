package com.potato.bookspud.domain.bookreport.controller;

import com.potato.bookspud.domain.auth.annotation.AccessTokenUser;
import com.potato.bookspud.domain.book.domain.Book;
import com.potato.bookspud.domain.book.domain.MyBook;
import com.potato.bookspud.domain.book.service.BookService;
import com.potato.bookspud.domain.bookmark.domain.BookMark;
import com.potato.bookspud.domain.bookmark.service.BookMarkService;
import com.potato.bookspud.domain.bookreport.dto.request.ArgumentCreateRequest;
import com.potato.bookspud.domain.bookreport.dto.request.ArgumentsRequest;
import com.potato.bookspud.domain.bookreport.dto.response.ArgumentCreateResponse;
import com.potato.bookspud.domain.bookreport.dto.response.ArgumentsResponse;
import com.potato.bookspud.domain.bookreport.dto.response.QuestionsResponse;
import com.potato.bookspud.domain.bookreport.service.BookReportService;
import com.potato.bookspud.domain.common.BaseResponse;
import com.potato.bookspud.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.potato.bookspud.domain.common.SuccessCode.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/bookreport")
public class BookReportController {
    private final BookReportService bookReportService;
    private final BookService bookService;
    private final BookMarkService bookMarkService;

    @GetMapping("/{id}/argument")
    public BaseResponse<ArgumentsResponse> getArguments(@PathVariable Long id, @AccessTokenUser User user){
        MyBook mybook = bookService.getMyBookByMybookId(id);
        List<BookMark> bookMarks = bookMarkService.getBookMarksByUserandBook(mybook, user);
        val result = bookReportService.getArguments(ArgumentsRequest.of(mybook, bookMarks));
        return BaseResponse.success(READ_ARGUMENTS_SUCCESS, result);
    }

    @PostMapping("/argument")
    public BaseResponse<ArgumentCreateResponse> createArgument (@RequestBody ArgumentCreateRequest request){
        val result = bookReportService.createBookReport(request);
        return BaseResponse.success(CREATE_BOOK_REPORT_SUCCESS, result);
    }

    @GetMapping("/{id}/question")
    public BaseResponse<QuestionsResponse> getQuestions(@PathVariable Long id){
        val result = bookReportService.getQuestions(id);
        return BaseResponse.success(READ_QUESTIONS_SUCCESS, result);
    }
}
