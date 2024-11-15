package com.api.wordToo.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
/*
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OpenAiClient {

    private final RestTemplate restTemplate;
    private final String apiKey;
    private final String apiUrl = "https://api.openai.com/v1/chat/completions";

    public OpenAiClient(RestTemplate restTemplate, @Value("${openai.api.key}") String apiKey) {
        this.restTemplate = restTemplate;
        this.apiKey = apiKey;
    }

    public String getChatCompletion(String message) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);

        // Construimos el cuerpo de la solicitud
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("model", "gpt-3.5-turbo");
        requestBody.put("messages", new Object[]{
                new HashMap<String, String>() {{
                    put("role", "user");
                    put("content", message);
                }}
        });

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestBody, headers);

        // Enviar la solicitud y recibir la respuesta
        Map<String, Object> response = restTemplate.exchange(apiUrl, HttpMethod.POST, request, Map.class).getBody();

        // Procesar y devolver la respuesta de OpenAI
        if (response != null && response.containsKey("choices")) {
            List<Map<String, Object>> choices = (List<Map<String, Object>>) response.get("choices");
            Map<String, Object> choice = choices.get(0);
            Map<String, Object> messageResponse = (Map<String, Object>) choice.get("message");
            return (String) messageResponse.get("content");
        }

        return null;
    }
}
*/
