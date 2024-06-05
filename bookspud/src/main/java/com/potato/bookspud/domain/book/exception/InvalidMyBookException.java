package com.potato.bookspud.domain.book.exception;

import com.potato.bookspud.domain.common.BaseException;
import com.potato.bookspud.domain.common.ErrorCode;

public class InvalidMyBookException extends BaseException {

    public InvalidMyBookException(ErrorCode errorCode) {super(errorCode, errorCode.getMessage());}
}
