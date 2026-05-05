package com.internship.tool.controller;

import com.internship.tool.service.RecordService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class RecordController {

    private final RecordService service;

    public RecordController(RecordService service) {
        this.service = service;
    }

    @PostMapping("/ai/generate")
    public Map<String, Object> generateAI(@RequestBody Map<String, String> req)
            throws Exception {

        return service.generateAI(
                req.get("text"),
                req.get("language")
        );
    }
}