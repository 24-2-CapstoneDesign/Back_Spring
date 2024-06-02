package com.potato.bookspud.domain.guide.service;

import com.potato.bookspud.domain.guide.domain.QuestionGuide;
import com.potato.bookspud.domain.guide.dto.response.QuestionGuideResponse;
import com.potato.bookspud.domain.guide.dto.response.QuestionGuidesResponse;
import com.potato.bookspud.domain.guide.repository.QuestionGuideRepository;
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
public class QuestionGuideService {
    private final QuestionGuideRepository questionGuideRepository;

    public QuestionGuidesResponse getQuestionGuide(){
        List<QuestionGuide> allQuestionGuides = questionGuideRepository.findAll();

        // 무작위로 섞기
        Collections.shuffle(allQuestionGuides);

        // 5개 선택하기
        List<QuestionGuideResponse> questionGuides = allQuestionGuides.stream()
                .limit(5)
                .map(QuestionGuideResponse::of)
                .toList();

        return QuestionGuidesResponse.of(questionGuides);
    }
}
