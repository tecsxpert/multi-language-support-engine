package com.internship.tool.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

@Service
public class CacheService {

    // In-memory cache
    private final ConcurrentHashMap<String, String> cache = new ConcurrentHashMap<>();

    // Generate cache key
    public String key(String input) {
        return "AI_" + input;
    }

    // Get cached value
    public String get(String key) {
        return cache.get(key);
    }

    // Save value to cache
    public void put(String key, String value) {
        cache.put(key, value);
    }
}