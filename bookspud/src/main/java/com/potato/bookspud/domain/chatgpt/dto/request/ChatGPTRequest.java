package com.potato.bookspud.domain.chatgpt.dto.request;

public record CompletionRequest(
        String model,
        String prompt,
        String temparature
) {
}
