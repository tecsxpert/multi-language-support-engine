package com.internship.tool.controller;

import com.internship.tool.repository.TranslationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/stats")
@RequiredArgsConstructor
public class TranslationStatsController {

    private final TranslationRepository translationRepository;

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Map<String, Object>> getStats() {
        Map<String, Object> stats = new HashMap<>();
        stats.put("totalTranslations", translationRepository.count());
        stats.put("completedTranslations",
            translationRepository.findByStatus("COMPLETED",
                org.springframework.data.domain.PageRequest.of(0, Integer.MAX_VALUE))
                .getTotalElements());
        stats.put("pendingTranslations",
            translationRepository.findByStatus("PENDING",
                org.springframework.data.domain.PageRequest.of(0, Integer.MAX_VALUE))
                .getTotalElements());
        stats.put("inProgressTranslations",
            translationRepository.findByStatus("IN_PROGRESS",
                org.springframework.data.domain.PageRequest.of(0, Integer.MAX_VALUE))
                .getTotalElements());
        return ResponseEntity.ok(stats);
    }
}