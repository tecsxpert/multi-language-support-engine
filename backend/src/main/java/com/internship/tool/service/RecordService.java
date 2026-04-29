package com.internship.tool.service;

import com.internship.tool.entity.RecordData;
import com.internship.tool.repository.RecordRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecordService {

    private final RecordRepository repo;
    private final AiServiceClient aiService;

    public RecordService(RecordRepository repo, AiServiceClient aiService) {
        this.repo = repo;
        this.aiService = aiService;
    }

    public RecordData create(RecordData record) {

        // ✅ Step 1: Save initial record (without result)
        record.setResult("Processing...");
        RecordData saved = repo.save(record);

        // ✅ Step 2: Call AI asynchronously
        aiService.getAiResponse(record.getInputText())
                .thenAccept(result -> {
                    saved.setResult(result != null ? result : "No result");
                    repo.save(saved);  // update with AI result
                });

        // ✅ Return immediately (non-blocking)
        return saved;
    }

    public List<RecordData> getAll() {
        return repo.findAll();
    }
}