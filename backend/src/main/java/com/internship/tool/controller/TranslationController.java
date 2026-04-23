package com.internship.tool.controller;

import com.internship.tool.entity.Translation;
import com.internship.tool.service.TranslationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/translations")
@RequiredArgsConstructor
public class TranslationController {

    private final TranslationService translationService;

    // GET /all — paginated
    @GetMapping("/all")
    public ResponseEntity<Page<Translation>> getAll(Pageable pageable) {
        log.info("GET /all called");
        return ResponseEntity.ok(translationService.getAll(pageable));
    }

    // GET /{id} — with 404 if not found
    @GetMapping("/{id}")
    public ResponseEntity<Translation> getById(@PathVariable Long id) {
        log.info("GET /{} called", id);
        return ResponseEntity.ok(translationService.getById(id));
    }

    // POST /create — with @Valid
    @PostMapping("/create")
    public ResponseEntity<Translation> create(
            @Valid @RequestBody Translation translation) {
        log.info("POST /create called");
        Translation created = translationService.create(translation);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // GET /search
    @GetMapping("/search")
    public ResponseEntity<Page<Translation>> search(
            @RequestParam String keyword, Pageable pageable) {
        return ResponseEntity.ok(translationService.search(keyword, pageable));
    }

    // GET /status
    @GetMapping("/status")
    public ResponseEntity<Page<Translation>> getByStatus(
            @RequestParam String status, Pageable pageable) {
        return ResponseEntity.ok(translationService.getByStatus(status, pageable));
    }
}