package com.potato.bookspud.domain.bookmark.controller;

import com.potato.bookspud.domain.auth.annotation.AccessTokenUser;
import com.potato.bookspud.domain.bookmark.domain.BookMark;
import com.potato.bookspud.domain.bookmark.dto.request.BookMarkCreateRequest;
import com.potato.bookspud.domain.bookmark.dto.response.*;
import com.potato.bookspud.domain.bookmark.service.BookMarkService;
import com.potato.bookspud.domain.common.BaseResponse;
import com.potato.bookspud.domain.common.Emotion;
import com.potato.bookspud.domain.common.SuccessCode;
import com.potato.bookspud.domain.user.domain.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
@Tag(name = "BookMark Controller", description = "북마크 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/bookmark")
public class BookMarkController {

    private final BookMarkService bookMarkService;
    @Operation(summary = "북마크 생성", description = "북마크 생성 API")
    @PostMapping
    public BaseResponse<BookMarkCreateResponse> createBookMark(@RequestBody BookMarkCreateRequest request,
                                                               @AccessTokenUser User user) {
        BookMark bookMark = bookMarkService.createBookMark(request, user);
        BookMarkCreateResponse result = BookMarkCreateResponse.of(bookMark.getId());
        return BaseResponse.success(SuccessCode.CREATE_BOOKMARK_SUCCESS, result);
    }

    @Operation(summary = "북마크 삭제", description = "등록된 북마크 삭제 API")
    @DeleteMapping("/{id}")
    public BaseResponse deleteBookMark(@PathVariable Long id){
        bookMarkService.deleteBookMark(id);
        return BaseResponse.success(SuccessCode.DELETE_BOOKMARK_SUCCESS);
    }

    @Operation(summary = "북마크 개별 조회", description = "북마크 개별 조회 API")
    @GetMapping("/{id}")
    public BaseResponse<BookMarkDetailResponse> getBookMarkDetail(@PathVariable Long id) {
        BookMarkDetailResponse result = bookMarkService.getBookMark(id);
        return BaseResponse.success(SuccessCode.READ_BOOKMARK_SUCCESS, result);
    }

    @Operation(summary = "책 별 북마크 목록 조회", description = "myBook 기준 개별 책 별 북마크 목록 조회 API")
    @GetMapping("/mybook/{mybookId}")
    public BaseResponse<List<BookMarkDetailResponse>> getBookmarksByMyBookId(@PathVariable Long mybookId) {
        List<BookMarkDetailResponse> result = bookMarkService.getBookmarksByMyBookId(mybookId);
        return BaseResponse.success(SuccessCode.READ_BOOKMARK_SUCCESS, result);
    }

    @Operation(summary = "특정 책, 특정 감정 북마크 목록 조회", description = "추천책 하나 선택 시 사용, 특정 책 기준 가장 많은 감정인 북마크 목록 조회 API")
    @GetMapping("/{bookId}/others")
    public BaseResponse<List<BookMarkOthersResponse>> getBookMarksByBookIdAndMostUsedEmotion(@PathVariable Long bookId, @AccessTokenUser User user) {
        List<BookMarkOthersResponse> result = bookMarkService.getBookMarksByBookIdAndMostUsedEmotion(bookId, user);
        return BaseResponse.success(SuccessCode.READ_BOOKMARK_SUCCESS, result);
    }

    @Operation(summary = "감정별 북마크 개수 조회", description = "감정 통계 그래프에서 사용, 사용자의 감정별 북마크 개수 조회 API")
    @GetMapping("/count")
    public BaseResponse<Map<Emotion, Long>> getBookMarkCountByEmotion(@AccessTokenUser User user) {
        Map<Emotion, Long> result = bookMarkService.getBookMarkCountByEmotion(user);
        return BaseResponse.success(SuccessCode.READ_BOOKMARK_SUCCESS, result);
    }

    @Operation(summary = "북마크 랜덤 조회", description = "메인 페이지에서 사용, 감정 및 사용자에 맞게 랜덤 북마크 하나 조회하는 API")
    @GetMapping("/random/{emotion}")
    public BaseResponse<RandomBookMarkResponse> getRandomBookMark(@PathVariable Emotion emotion, @AccessTokenUser User user) {
        RandomBookMarkResponse result = bookMarkService.getRandomBookMark(emotion, user);
        return BaseResponse.success(SuccessCode.READ_BOOKMARK_SUCCESS, result);
    }
}
