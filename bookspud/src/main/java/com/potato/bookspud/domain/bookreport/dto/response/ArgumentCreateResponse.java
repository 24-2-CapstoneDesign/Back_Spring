package com.potato.bookspud.domain.bookreport.dto.response;

import lombok.Builder;

@Builder
public record ArgumentCreateResponse(
        Long id
) {
    public static ArgumentCreateResponse of (Long id){
        return ArgumentCreateResponse.builder()
                .id(id)
                .build();
    }
}
