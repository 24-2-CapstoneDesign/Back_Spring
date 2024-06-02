package com.potato.bookspud.domain.guide.controller;

import com.potato.bookspud.domain.common.BaseResponse;
import com.potato.bookspud.domain.common.Emotion;
import com.potato.bookspud.domain.guide.domain.VocaGuide;
import com.potato.bookspud.domain.guide.dto.response.QuestionGuidesResponse;
import com.potato.bookspud.domain.guide.dto.response.VocaGuidesResponse;
import com.potato.bookspud.domain.guide.service.QuestionGuideService;
import com.potato.bookspud.domain.guide.service.VocaGuideService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.potato.bookspud.domain.common.SuccessCode.READ_QUESTION_GUIDE_SUCCESS;
import static com.potato.bookspud.domain.common.SuccessCode.READ_VOCA_GUIDE_SUCCESS;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/guide")
public class GuideController {
    private final VocaGuideService vocaGuideService;
    private final QuestionGuideService questionGuideService;

    @GetMapping("/voca/{emotion}")
    public BaseResponse<VocaGuidesResponse> getVocaGuide(@PathVariable Emotion emotion){
        val result = vocaGuideService.getVocaGuide(emotion);
        return BaseResponse.success(READ_VOCA_GUIDE_SUCCESS, result);
    }

    @GetMapping("/question")
    public BaseResponse<QuestionGuidesResponse> getQuestionGuide(){
        val result = questionGuideService.getQuestionGuide();
        return BaseResponse.success(READ_QUESTION_GUIDE_SUCCESS, result);
    }
}
