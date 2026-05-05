package com.internship.tool.controller;

import com.internship.tool.service.RecordService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(
        controllers = RecordController.class,
        excludeAutoConfiguration = SecurityAutoConfiguration.class
)
@ActiveProfiles("test")
class RecordControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RecordService recordService;

    @Test
    void testGetAllRecords() throws Exception {
        mockMvc.perform(get("/api/records"))
                .andExpect(status().isOk())
                .andExpect(content().string("Records retrieved successfully"));
    }
}