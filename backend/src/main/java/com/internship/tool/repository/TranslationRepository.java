package com.internship.tool.repository;

import com.internship.tool.entity.Translation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TranslationRepository extends JpaRepository<Translation, Long> {

    Page<Translation> findBySourceTextContainingIgnoreCase(String keyword, Pageable pageable);

    Page<Translation> findByStatus(String status, Pageable pageable);

    List<Translation> findBySourceLanguage(String language);

    List<Translation> findByTargetLanguage(String language);
}