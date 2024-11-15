package com.api.wordToo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.wordToo.service.OpenAiClient;

@RestController
public class ChatController {

    private final OpenAiClient openAiClient;

    public ChatController(OpenAiClient openAiClient) {
        this.openAiClient = openAiClient;
    }

    @GetMapping("/chat")
    public String chat(@RequestParam String message) {
        return openAiClient.getChatCompletion(message);
    }
}