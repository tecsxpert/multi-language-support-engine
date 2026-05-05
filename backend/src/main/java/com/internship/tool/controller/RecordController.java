package com.internship.tool.controller;

import com.internship.tool.service.RecordService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/records")
public class RecordController {

    private final RecordService recordService;

    public RecordController(RecordService recordService) {
        this.recordService = recordService;
    }

    @GetMapping("/ai")
    public ResponseEntity<?> generateAI(
            @RequestParam String text,
            @RequestParam String language) {

        Map<String, Object> response = recordService.generateAI(text, language);
        return ResponseEntity.ok(response);
    }
}