package com.potato.bookspud.domain.user.exception;

import com.potato.bookspud.domain.common.BaseException;
import com.potato.bookspud.domain.common.ErrorCode;

public class InvalidUserException extends BaseException {
    public InvalidUserException(ErrorCode errorCode) {
        super(errorCode, errorCode.getMessage());
    }
}
