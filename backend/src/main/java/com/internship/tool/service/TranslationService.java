package com.internship.tool.service;

import com.internship.tool.entity.Translation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface TranslationService {

    Translation create(Translation translation);

    Translation getById(Long id);

    Page<Translation> getAll(Pageable pageable);

    Translation update(Long id, Translation translation);

    void delete(Long id);

    Page<Translation> search(String keyword, Pageable pageable);

    List<Translation> getBySourceLanguage(String sourceLanguage);

    List<Translation> getByTargetLanguage(String targetLanguage);

    Page<Translation> getByStatus(String status, Pageable pageable);
}