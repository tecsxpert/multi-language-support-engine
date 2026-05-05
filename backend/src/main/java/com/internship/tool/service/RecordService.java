package com.internship.tool.service;

import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ExecutionException;

@Service
public class RecordService {

    private final AiServiceClient aiClient;

    public RecordService(AiServiceClient aiClient) {
        this.aiClient = aiClient;
    }

    public Map<String, Object> generateAI(String text, String language)
            throws ExecutionException, InterruptedException {

        return aiClient.generate(text, language).get();
    }
}