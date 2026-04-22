package com.internship.tool.repository;

import com.internship.tool.entity.Translation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TranslationRepository extends JpaRepository<Translation, Long> {

    // Find by source language
    List<Translation> findBySourceLanguage(String sourceLanguage);

    // Find by target language
    List<Translation> findByTargetLanguage(String targetLanguage);

    // Find by status
    Page<Translation> findByStatus(String status, Pageable pageable);

    // Search in source or translated text
    @Query("SELECT t FROM Translation t WHERE " +
           "LOWER(t.sourceText) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(t.translatedText) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    Page<Translation> searchByKeyword(@Param("keyword") String keyword, Pageable pageable);

    // Find by language pair
    List<Translation> findBySourceLanguageAndTargetLanguage(
        String sourceLanguage, String targetLanguage);
}