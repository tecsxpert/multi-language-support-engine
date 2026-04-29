package com.internship.tool.controller;

import com.internship.tool.entity.RecordData;
import com.internship.tool.service.RecordService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RecordController {

    private final RecordService service;

    // ✅ Constructor Injection
    public RecordController(RecordService service) {
        this.service = service;
    }

    // ✅ CREATE API
    @PostMapping("/create")
    public RecordData create(@RequestBody RecordData record) {
        return service.create(record);
    }

    // ✅ GET ALL API
    @GetMapping("/all")
    public List<RecordData> getAll() {
        return service.getAll();
    }
}