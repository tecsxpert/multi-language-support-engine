package com.internship.tool.service;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Component
public class AiServiceClient {

    private final RestTemplate restTemplate;

    public AiServiceClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String callGroq(String text, String language) {

        String url = "http://localhost:5000/groq"; // your Groq endpoint

        Map<String, String> request = new HashMap<>();
        request.put("text", text);
        request.put("language", language);

        Map<?, ?> response = restTemplate.postForObject(url, request, Map.class);

        if (response == null || response.get("result") == null) {
            throw new RuntimeException("Groq response invalid");
        }

        return String.valueOf(response.get("result"));
    }
}