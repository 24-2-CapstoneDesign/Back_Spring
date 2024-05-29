package com.potato.bookspud.domain.auth.dto.response;

public class SuccessResponse<T> {
    private String message;
    private T result;

    private SuccessResponse(String message, T result) {
        this.message = message;
        this.result = result;
    }

    public static <T> SuccessResponse<T> of(String message, T result) {
        return new SuccessResponse<>(message, result);
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return result;
    }
}

