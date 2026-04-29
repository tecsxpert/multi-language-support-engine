package com.internship.tool.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Service
public class AiServiceClient {

    private final RestTemplate restTemplate = new RestTemplate();

    @Async
    public CompletableFuture<String> getAiResponse(String input) {

        try {
            String url = "http://127.0.0.1:5000/ai/describe";

            Map<String, String> request = Map.of("text", input);

            Map response = restTemplate.postForObject(url, request, Map.class);

            if (response != null && response.get("result") != null) {
                return CompletableFuture.completedFuture(
                        (String) response.get("result")
                );
            } else {
                return CompletableFuture.completedFuture("AI returned empty response");
            }

        } catch (Exception e) {
            return CompletableFuture.completedFuture("AI Service Not Available");
        }
    }
}