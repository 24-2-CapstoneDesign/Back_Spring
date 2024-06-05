package com.potato.bookspud.domain.chatgpt.controller;

import com.potato.bookspud.domain.chatgpt.service.ChatGPTService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ChatGPTController {
    private final ChatGPTService chatGPTService;

    public String makeArguments(List<String> info){
        String instruction = "이 정보들을 바탕으로 한 독서록을 쓸건데, 제목을 지어줬으면 좋겠어. 간단한 주제를 담은 제목을 5개 생성해줘. 이때 각 문장은 개행문자로 구분해서 줘. 숫자는 붙이지 말고 제목만 줘";
        return chatGPTService.chat(info, instruction);
    }

    public String makeQuestions(List<String> info){
        String instruction = "이 정보들을 바탕으로 서론, 본론, 결론으로 나누어서 질문을 각각 1개씩 만들어줘. 이때 각 질문은 개행문자로 구분해서 줘";
        return chatGPTService.chat(info, instruction);
    }
}
