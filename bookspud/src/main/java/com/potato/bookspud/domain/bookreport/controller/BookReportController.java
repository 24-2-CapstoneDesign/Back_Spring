package com.potato.bookspud.domain.bookreport.controller;

import com.potato.bookspud.domain.auth.annotation.AccessTokenUser;
import com.potato.bookspud.domain.book.domain.MyBook;
import com.potato.bookspud.domain.book.service.BookService;
import com.potato.bookspud.domain.bookmark.domain.BookMark;
import com.potato.bookspud.domain.bookmark.service.BookMarkService;
import com.potato.bookspud.domain.bookreport.dto.request.ArgumentCreateRequest;
import com.potato.bookspud.domain.bookreport.dto.request.ArgumentsRequest;
import com.potato.bookspud.domain.bookreport.dto.request.DraftCreateRequest;
import com.potato.bookspud.domain.bookreport.dto.request.FinalCreateRequest;
import com.potato.bookspud.domain.bookreport.dto.response.ArgumentCreateResponse;
import com.potato.bookspud.domain.bookreport.dto.response.ArgumentsResponse;
import com.potato.bookspud.domain.bookreport.dto.response.DraftResponse;
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
    @GetMapping("/{myBookId}/argument")
    public BaseResponse<ArgumentsResponse> getArguments(@PathVariable Long myBookId, @AccessTokenUser User user){
        MyBook myBook = bookService.getMyBookByMybookId(myBookId);
        List<BookMark> bookMarks = bookMarkService.getBookMarksByUserandBook(myBook, user);
        val result = bookReportService.getArguments(ArgumentsRequest.of(myBook, bookMarks));
        return BaseResponse.success(READ_ARGUMENTS_SUCCESS, result);
    }

    @Operation(summary = "논점 선택", description = "논점 선택하는 API")
    @PostMapping("{myBookId}/argument")
    public BaseResponse<ArgumentCreateResponse> createArgument (@PathVariable Long myBookId, @RequestBody ArgumentCreateRequest request){
        MyBook myBook = bookService.getMyBookByMybookId(myBookId);
        val result = bookReportService.createBookReport(myBook, request);
        return BaseResponse.success(CREATE_BOOK_REPORT_SUCCESS, result);
    }

    @Operation(summary = "질문 조회", description = "질문 조회하는 API (GPT)")
    @GetMapping("/{id}/question")
    public BaseResponse<QuestionsResponse> getQuestions(@PathVariable Long id){
        val result = bookReportService.getQuestions(id);
        return BaseResponse.success(READ_QUESTIONS_SUCCESS, result);
    }

    @Operation(summary = "초안 생성", description = "초안 생성하는 API (GPT)")
    @PatchMapping("/{id}/draft")
    public BaseResponse<DraftResponse> createDraft(@PathVariable Long id, @RequestBody DraftCreateRequest request){
        val result = bookReportService.createDraft(id, request);
        return BaseResponse.success(CREATE_DRAFT_SUCCESS, result);
    }

    @Operation(summary = "최종본 생성", description = "최종본 생성하는 API")
    @PatchMapping("/{id}")
    public BaseResponse createFinal(@PathVariable Long id, @RequestBody FinalCreateRequest request){
        bookReportService.createFinal(id, request);
        return BaseResponse.success(CREATE_FINAL_SUCCESS);
    }

}
