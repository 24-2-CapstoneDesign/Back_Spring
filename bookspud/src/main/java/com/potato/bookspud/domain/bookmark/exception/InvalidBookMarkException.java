package com.potato.bookspud.domain.bookmark.exception;

import com.potato.bookspud.domain.common.BaseException;
import com.potato.bookspud.domain.common.ErrorCode;

public class InvalidBookMarkException extends BaseException {
    public InvalidBookMarkException(ErrorCode errorCode) {
        super(errorCode, errorCode.getMessage());
    }
}
