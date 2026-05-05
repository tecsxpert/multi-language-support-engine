package com.internship.tool.service;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.client.SimpleClientHttpRequestFactory;

import java.util.HashMap;
import java.util.Map;

@Service
public class AiServiceClient {

    private final RestTemplate restTemplate;

    private static final String BASE_URL = "http://127.0.0.1:5000";

    public AiServiceClient() {
        this.restTemplate = createRestTemplate();
    }

    // ✅ 10 seconds timeout
    private RestTemplate createRestTemplate() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(10000);
        factory.setReadTimeout(10000);
        return new RestTemplate(factory);
    }

    // ✅ /chat API
    public String callChat(String message) {
        try {
            String url = BASE_URL + "/chat";

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            Map<String, String> body = new HashMap<>();
            body.put("message", message);

            HttpEntity<Map<String, String>> request = new HttpEntity<>(body, headers);

            ResponseEntity<Map> response =
                    restTemplate.postForEntity(url, request, Map.class);

            return (String) response.getBody().get("response");

        } catch (Exception e) {
            return null;   // ✅ REQUIRED
        }
    }

    // ✅ /ai/describe API
    public String describe(String input) {
        try {
            String url = BASE_URL + "/ai/describe";

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            Map<String, String> body = new HashMap<>();
            body.put("input", input);

            HttpEntity<Map<String, String>> request = new HttpEntity<>(body, headers);

            ResponseEntity<Map> response =
                    restTemplate.postForEntity(url, request, Map.class);

            return (String) response.getBody().get("response");

        } catch (Exception e) {
            return null;
        }
    }

    // ✅ /ai/recommend API
    public String recommend(String input) {
        try {
            String url = BASE_URL + "/ai/recommend";

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            Map<String, String> body = new HashMap<>();
            body.put("input", input);

            HttpEntity<Map<String, String>> request = new HttpEntity<>(body, headers);

            ResponseEntity<Map> response =
                    restTemplate.postForEntity(url, request, Map.class);

            return (String) response.getBody().get("response");

        } catch (Exception e) {
            return null;
        }
    }
}