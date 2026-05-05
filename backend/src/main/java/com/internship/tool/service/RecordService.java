package com.internship.tool.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class RecordService {

    private final AiServiceClient aiServiceClient;

    public RecordService(AiServiceClient aiServiceClient) {
        this.aiServiceClient = aiServiceClient;
    }

    public Map<String, Object> generateAI(String text, String language) {

        long start = System.currentTimeMillis();

        try {
            String result = aiServiceClient.callGroq(text, language);

            long elapsed = System.currentTimeMillis() - start;

            // Soft SLA: if too slow, return fallback
            if (elapsed > 2000) {
                return fallback(text);
            }

            Map<String, Object> ok = new HashMap<>();
            ok.put("result", result);
            ok.put("is_fallback", false);
            return ok;

        } catch (Exception e) {
            return fallback(text);
        }
    }

    private Map<String, Object> fallback(String text) {
        Map<String, Object> fb = new HashMap<>();
        fb.put("result", "AI service unavailable. Basic response for: " + text);
        fb.put("is_fallback", true);
        return fb;
    }
}