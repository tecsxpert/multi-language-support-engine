package com.internship.tool;

import com.internship.tool.entity.Translation;
import com.internship.tool.repository.TranslationRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class TranslationControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private TranslationRepository translationRepository;

    @BeforeEach
    void setUp() {
        translationRepository.deleteAll();
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void testCreateTranslation_Success() throws Exception {
        Translation translation = new Translation();
        translation.setSourceText("Hello");
        translation.setTranslatedText("Hola");
        translation.setSourceLanguage("en");
        translation.setTargetLanguage("es");
        translation.setStatus("COMPLETED");

        mockMvc.perform(post("/api/translations/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(translation)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.sourceText").value("Hello"));
    }

    @Test
    @WithMockUser(roles = "USER")
    void testGetAll_Success() throws Exception {
        mockMvc.perform(get("/api/translations/all"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "USER")
    void testGetById_NotFound() throws Exception {
        mockMvc.perform(get("/api/translations/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testGetAll_Unauthorized() throws Exception {
        mockMvc.perform(get("/api/translations/all"))
                .andExpect(status().isUnauthorized());
    }
}