package com.potato.bookspud.domain.guide.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public record VocaGuidesResponse(
        List<VocaGuideResponse> vocaGuides
) {
    public static VocaGuidesResponse of(List<VocaGuideResponse> vocaGuides){
        return VocaGuidesResponse.builder()
                .vocaGuides(vocaGuides)
                .build();
    }
}
