package com.internship.tool.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

@Service
public class AiServiceClient {

    private final ExecutorService executor = Executors.newFixedThreadPool(5);

    // Simulate external AI call (replace with Groq API later)
    private String callExternalAI(String text, String language) throws Exception {
        // simulate latency
        Thread.sleep(500);

        // simulate occasional failure
        if (text.toLowerCase().contains("fail")) {
            throw new RuntimeException("AI service failed");
        }

        return "AI response for: " + text + " in " + language;
    }

    public CompletableFuture<Map<String, Object>> generate(String text, String language) {

        return CompletableFuture.supplyAsync(() -> {
            Map<String, Object> res = new HashMap<>();

            try {
                // ⏱ enforce timeout (2 seconds target)
                String aiResponse = CompletableFuture
                        .supplyAsync(() -> {
                            try {
                                return callExternalAI(text, language);
                            } catch (Exception e) {
                                throw new CompletionException(e);
                            }
                        }, executor)
                        .orTimeout(2, TimeUnit.SECONDS)
                        .join();

                res.put("data", aiResponse);
                res.put("is_fallback", false);

            } catch (Exception e) {
                // 🔥 fallback
                res.put("data", "Fallback response for: " + text);
                res.put("is_fallback", true);
            }

            return res;
        }, executor);
    }
}