package com.potato.bookspud.domain.bookreport.controller;

import com.potato.bookspud.domain.auth.annotation.AccessTokenUser;
import com.potato.bookspud.domain.book.domain.Book;
import com.potato.bookspud.domain.book.domain.MyBook;
import com.potato.bookspud.domain.book.service.BookService;
import com.potato.bookspud.domain.bookmark.domain.BookMark;
import com.potato.bookspud.domain.bookmark.service.BookMarkService;
import com.potato.bookspud.domain.bookreport.dto.request.AnswersRequest;
import com.potato.bookspud.domain.bookreport.dto.request.ArgumentCreateRequest;
import com.potato.bookspud.domain.bookreport.dto.request.ArgumentsRequest;
import com.potato.bookspud.domain.bookreport.dto.response.ArgumentCreateResponse;
import com.potato.bookspud.domain.bookreport.dto.response.ArgumentsResponse;
import com.potato.bookspud.domain.bookreport.dto.response.QuestionsResponse;
import com.potato.bookspud.domain.bookreport.service.BookReportService;
import com.potato.bookspud.domain.common.BaseResponse;
import com.potato.bookspud.domain.user.domain.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.potato.bookspud.domain.common.SuccessCode.*;

@Tag(name = "BookReport Controller", description = "독후감 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/bookreport")
public class BookReportController {
    private final BookReportService bookReportService;
    private final BookService bookService;
    private final BookMarkService bookMarkService;

    @Operation(summary = "논점 후보 조회", description = "논점 후보 조회하는 API (GPT)")
    @GetMapping("/{id}/argument")
    public BaseResponse<ArgumentsResponse> getArguments(@PathVariable Long id, @AccessTokenUser User user){
        MyBook myBook = bookService.getMyBookByMybookId(id);
        List<BookMark> bookMarks = bookMarkService.getBookMarksByUserandBook(myBook, user);
        val result = bookReportService.getArguments(ArgumentsRequest.of(myBook, bookMarks));
        return BaseResponse.success(READ_ARGUMENTS_SUCCESS, result);
    }

    @Operation(summary = "논점 선택", description = "논점 선택하는 API")
    @PostMapping("{id}/argument")
    public BaseResponse<ArgumentCreateResponse> createArgument (@PathVariable Long id, @RequestBody ArgumentCreateRequest request){
        MyBook myBook = bookService.getMyBookByMybookId(id);
        val result = bookReportService.createBookReport(myBook, request);
        return BaseResponse.success(CREATE_BOOK_REPORT_SUCCESS, result);
    }

    @Operation(summary = "질문 조회", description = "서론, 본론, 결론 질문 조회하는 API (GPT)")
    @GetMapping("/{id}/question")
    public BaseResponse<QuestionsResponse> getQuestions(@PathVariable Long id){
        val result = bookReportService.getQuestions(id);
        return BaseResponse.success(READ_QUESTIONS_SUCCESS, result);
    }

    @Operation(summary = "답변 생성", description = "서론, 본론, 결론 답변 생성하는 API")
    @PatchMapping("/{id}/answer")
    public BaseResponse createAnswers(@PathVariable Long id, @RequestBody AnswersRequest request){
        bookReportService.createAnswers(id, request);
        return BaseResponse.success(CREATE_ANSWERS_SUCCESS);
    }
}
