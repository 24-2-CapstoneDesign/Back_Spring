package com.potato.bookspud.domain.bookreport.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public record ArgumentsResponse(
        List<String> arguments
) {
    public static ArgumentsResponse of (List<String> arguments){
        return ArgumentsResponse.builder()
                .arguments(arguments)
                .build();
    }
}
