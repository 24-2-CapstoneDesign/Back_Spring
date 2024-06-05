package com.potato.bookspud.domain.chatgpt.dto.request;

import com.potato.bookspud.domain.chatgpt.dto.response.ChatGPTResponse;
import lombok.Builder;

import java.util.List;

@Builder
public record ChatGPTRequest(
        String model,
        List<Message> messages,
        Float temperature

) {
    public static ChatGPTRequest of (String model, List<Message> messages, Float temperature){
        return ChatGPTRequest.builder()
                .model(model)
                .messages(messages)
                .temperature(temperature)
                .build();
    }

}
