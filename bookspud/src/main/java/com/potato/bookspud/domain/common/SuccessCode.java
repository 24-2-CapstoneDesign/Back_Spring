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
    CREATE_BOOK_SUCCESS(OK, "책 생성에 성공하였습니다.");

    private final HttpStatus httpStatus;
    String message;

    public int getHTTPStatusCode(){
        return httpStatus.value();
    }
}
