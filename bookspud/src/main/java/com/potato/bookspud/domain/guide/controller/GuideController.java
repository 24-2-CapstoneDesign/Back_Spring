package com.potato.bookspud.domain.guide.controller;

import com.potato.bookspud.domain.common.BaseResponse;
import com.potato.bookspud.domain.common.Emotion;
import com.potato.bookspud.domain.guide.domain.VocaGuide;
import com.potato.bookspud.domain.guide.dto.response.QuestionGuidesResponse;
import com.potato.bookspud.domain.guide.dto.response.VocaGuidesResponse;
import com.potato.bookspud.domain.guide.service.QuestionGuideService;
import com.potato.bookspud.domain.guide.service.VocaGuideService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.potato.bookspud.domain.common.SuccessCode.READ_QUESTION_GUIDE_SUCCESS;
import static com.potato.bookspud.domain.common.SuccessCode.READ_VOCA_GUIDE_SUCCESS;

@Tag(name = "Guide Controller", description = "어휘, 질문 가이드 관련 API (최종본 생성시 사용)")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/guide")
public class GuideController {
    private final VocaGuideService vocaGuideService;
    private final QuestionGuideService questionGuideService;

    @Operation(summary = "추천 어휘 조회", description = "독서록 작성 시 감정별 추천 어휘를 랜덤으로 5개 제공하는 API")
    @GetMapping("/voca/{emotion}")
    public BaseResponse<VocaGuidesResponse> getVocaGuide(@PathVariable Emotion emotion){
        val result = vocaGuideService.getVocaGuide(emotion);
        return BaseResponse.success(READ_VOCA_GUIDE_SUCCESS, result);
    }

    @Operation(summary = "추천 질문 조회", description = "독서록 작성 시 가이드 질문을 랜덤으로 5개 제공하는 API")
    @GetMapping("/question")
    public BaseResponse<QuestionGuidesResponse> getQuestionGuide(){
        val result = questionGuideService.getQuestionGuide();
        return BaseResponse.success(READ_QUESTION_GUIDE_SUCCESS, result);
    }
}
