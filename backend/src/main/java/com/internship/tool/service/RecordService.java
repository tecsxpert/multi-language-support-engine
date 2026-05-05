package com.internship.tool.service;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RecordService {

    public List<String> getAllRecords() {
        return List.of("Sample Record");
    }
}