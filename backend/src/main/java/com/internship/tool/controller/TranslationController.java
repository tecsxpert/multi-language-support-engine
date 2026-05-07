package com.internship.tool.controller;

import com.internship.tool.entity.Translation;
import com.internship.tool.service.TranslationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/translations")
@RequiredArgsConstructor
public class TranslationController {

    private final TranslationService translationService;

    @GetMapping("/all")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Page<Translation>> getAll(Pageable pageable) {
        log.info("GET /all called");
        return ResponseEntity.ok(translationService.getAll(pageable));
    }

    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Translation> getById(@PathVariable Long id) {
        log.info("GET /{} called", id);
        return ResponseEntity.ok(translationService.getById(id));
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Translation> create(
            @Valid @RequestBody Translation translation) {
        log.info("POST /create called");
        Translation created = translationService.create(translation);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Translation> update(
            @PathVariable Long id,
            @Valid @RequestBody Translation translation) {
        log.info("PUT /{} called", id);
        return ResponseEntity.ok(translationService.update(id, translation));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info("DELETE /{} called", id);
        translationService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Page<Translation>> search(
            @RequestParam String keyword, Pageable pageable) {
        return ResponseEntity.ok(translationService.search(keyword, pageable));
    }

    @GetMapping("/status")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Page<Translation>> getByStatus(
            @RequestParam String status, Pageable pageable) {
        return ResponseEntity.ok(translationService.getByStatus(status, pageable));
    }

    @GetMapping("/language/source")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<Translation>> getBySourceLanguage(
            @RequestParam String sourceLanguage) {
        return ResponseEntity.ok(
            translationService.getBySourceLanguage(sourceLanguage));
    }

    @GetMapping("/language/target")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<Translation>> getByTargetLanguage(
            @RequestParam String targetLanguage) {
        return ResponseEntity.ok(
            translationService.getByTargetLanguage(targetLanguage));
    }

    @GetMapping("/export")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<byte[]> exportCsv() {
        log.info("GET /export called");
        List<Translation> all = translationService.getAll(
            Pageable.unpaged()).getContent();

        StringBuilder csv = new StringBuilder();
        csv.append("ID,Source Text,Translated Text,Source Language,Target Language,Status,Created At\n");
        for (Translation t : all) {
            csv.append(t.getId()).append(",")
               .append(escapeCSV(t.getSourceText())).append(",")
               .append(escapeCSV(t.getTranslatedText())).append(",")
               .append(t.getSourceLanguage()).append(",")
               .append(t.getTargetLanguage()).append(",")
               .append(t.getStatus()).append(",")
               .append(t.getCreatedAt()).append("\n");
        }

        byte[] csvBytes = csv.toString().getBytes();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("text/csv"));
        headers.setContentDispositionFormData("attachment", "translations.csv");
        headers.setContentLength(csvBytes.length);

        return ResponseEntity.ok()
            .headers(headers)
            .body(csvBytes);
    }

    private String escapeCSV(String value) {
        if (value == null) return "";
        if (value.contains(",") || value.contains("\"") || value.contains("\n")) {
            return "\"" + value.replace("\"", "\"\"") + "\"";
        }
        return value;
    }
}