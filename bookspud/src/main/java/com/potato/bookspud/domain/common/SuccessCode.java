package com.potato.bookspud.domain.common;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.OK;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum SuccessCode {
    // 200 OK
    CREATE_BOOK_SUCCESS(OK, "책 생성에 성공하였습니다."),
    READ_BOOK_SUCCESS(OK, "책 조회에 성공했습니다."),
    UPDATE_BOOK_PROCESS_SUCCESS(OK, "책 진행 상황 업데이트에 성공했습니다."),
    DELETE_BOOK_SUCCESS(OK, "책 삭제에 성공했습니다."),
    READ_RECOMMENDATION_BOOK_SUCCESS(OK, "추천 책 조회에 성공했습니다."),
    READ_VOCA_GUIDE_SUCCESS(OK, "단어 가이드 조회에 성공했습니다."),
    READ_QUESTION_GUIDE_SUCCESS(OK, "질문 가이드 조회에 성공했습니다.");


    private final HttpStatus httpStatus;
    String message;

    public int getHTTPStatusCode(){
        return httpStatus.value();
    }
}
