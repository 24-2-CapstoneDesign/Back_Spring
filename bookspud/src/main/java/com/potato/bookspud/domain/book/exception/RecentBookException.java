package com.potato.bookspud.domain.book.exception;

import com.potato.bookspud.domain.common.BaseException;
import com.potato.bookspud.domain.common.ErrorCode;
import lombok.Getter;

@Getter
public class RecentBookException extends BaseException {
    public RecentBookException(ErrorCode error){
        super(error, "[RecentBookException]" + error.getMessage());
    }
}
