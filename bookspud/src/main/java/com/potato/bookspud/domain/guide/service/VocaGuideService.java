package com.potato.bookspud.domain.guide.service;

import com.potato.bookspud.domain.common.Emotion;
import com.potato.bookspud.domain.guide.domain.VocaGuide;
import com.potato.bookspud.domain.guide.dto.response.QuestionGuideResponse;
import com.potato.bookspud.domain.guide.dto.response.VocaGuideResponse;
import com.potato.bookspud.domain.guide.dto.response.VocaGuidesResponse;
import com.potato.bookspud.domain.guide.repository.VocaGuideRepository;
import jakarta.transaction.Transactional;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@Builder
@RequiredArgsConstructor
@Transactional
public class VocaGuideService {
    private final VocaGuideRepository vocaGuideRepository;

    public VocaGuidesResponse getVocaGuide(Emotion emotion){
        List<VocaGuide> allVocaGuides = vocaGuideRepository.findAllByEmotion(emotion);
        Collections.shuffle(allVocaGuides);

        // 5개 선택하기
        List<VocaGuideResponse> vocaGuides = allVocaGuides.stream()
                .limit(5)
                .map(VocaGuideResponse::of)
                .toList();

        return VocaGuidesResponse.of(vocaGuides);
    }
}
