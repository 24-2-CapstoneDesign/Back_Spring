package com.potato.bookspud.domain.chatgpt.service;

import com.potato.bookspud.domain.chatgpt.dto.request.ChatGPTRequest;
import com.potato.bookspud.domain.chatgpt.dto.request.Message;
import com.potato.bookspud.domain.chatgpt.dto.response.ChatGPTResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChatGPTService {
    @Value("${openai.model}")
    private String model;

    @Value("${openai.api.url}")
    private String apiURL;

    @Autowired
    private RestTemplate restTemplate;

    private List<Message> makeMessage(List<String> contents, String instruction){
        List<Message> messages = new ArrayList<>();

        // system 메시지 추가
        messages.add(new Message("system", "너는 중학교 2학년에게 독서 교육을 해주는 선생님이자 독서 박사야"));
        // user 메시지 추가
        for(String content : contents){
            messages.add(new Message("user", content));
        }
        messages.add(new Message("user", instruction));
        return messages;
    }

    public String chat(List<String> contents, String instruction){
        List<Message> messages = makeMessage(contents, instruction);

        ChatGPTRequest gptRequest = ChatGPTRequest.of(model, messages, 0.1f);

        ChatGPTResponse chatGPTResponse = restTemplate.postForObject(apiURL, gptRequest, ChatGPTResponse.class);
        if (chatGPTResponse != null && !chatGPTResponse.choices().isEmpty()){
            return chatGPTResponse.choices().get(0).message().content();
        }
        return null;
    }
}
