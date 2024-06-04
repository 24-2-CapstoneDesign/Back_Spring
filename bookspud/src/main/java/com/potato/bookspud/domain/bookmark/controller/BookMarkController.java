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
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/bookmark")
public class BookMarkController {

    private final BookMarkService bookMarkService;
    @PostMapping
    public BaseResponse<BookMarkCreateResponse> createBookMark(@RequestBody BookMarkCreateRequest request,
                                                               @AccessTokenUser User user) {
        BookMark bookMark = bookMarkService.createBookMark(request, user);
        BookMarkCreateResponse result = BookMarkCreateResponse.of(bookMark.getId());
        return BaseResponse.success(SuccessCode.CREATE_BOOKMARK_SUCCESS, result);
    }

    @DeleteMapping("/{id}")
    public BaseResponse deleteBookMark(@PathVariable Long id){
        bookMarkService.deleteBookMark(id);
        return BaseResponse.success(SuccessCode.DELETE_BOOKMARK_SUCCESS);
    }

    @GetMapping("/{id}")
    public BaseResponse<BookMarkDetailResponse> getBookMarkDetail(@PathVariable Long id) {
        BookMarkDetailResponse result = bookMarkService.getBookMark(id);
        return BaseResponse.success(SuccessCode.READ_BOOKMARK_SUCCESS, result);
    }

    @GetMapping("/mybook/{mybookId}")
    public BaseResponse<List<BookMarkDetailResponse>> getBookmarksByMyBookId(@PathVariable Long mybookId) {
        List<BookMarkDetailResponse> result = bookMarkService.getBookmarksByMyBookId(mybookId);
        return BaseResponse.success(SuccessCode.READ_BOOKMARK_SUCCESS, result);
    }

    @GetMapping("/{bookId}/others")
    public BaseResponse<List<BookMarkOthersResponse>> getBookMarksByBookIdAndMostUsedEmotion(@PathVariable Long bookId, @AccessTokenUser User user) {
        List<BookMarkOthersResponse> result = bookMarkService.getBookMarksByBookIdAndMostUsedEmotion(bookId, user);
        return BaseResponse.success(SuccessCode.READ_BOOKMARK_SUCCESS, result);
    }

    @GetMapping("/count")
    public BaseResponse<Map<Emotion, Long>> getBookMarkCountByEmotion(@AccessTokenUser User user) {
        Map<Emotion, Long> result = bookMarkService.getBookMarkCountByEmotion(user);
        return BaseResponse.success(SuccessCode.READ_BOOKMARK_SUCCESS, result);
    }

    @GetMapping("/random/{emotion}")
    public BaseResponse<RandomBookMarkResponse> getRandomBookMark(@PathVariable Emotion emotion, @AccessTokenUser User user) {
        RandomBookMarkResponse result = bookMarkService.getRandomBookMark(emotion, user);
        return BaseResponse.success(SuccessCode.READ_BOOKMARK_SUCCESS, result);
    }
}
