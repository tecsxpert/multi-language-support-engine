package com.internship.tool.service;

import com.internship.tool.entity.Translation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TranslationService {

    Translation create(Translation translation);

    Translation getById(Long id);

    List<Translation> getAll();

    void delete(Long id);

    // pagination
    Page<Translation> getAll(Pageable pageable);

    // update
    Translation update(Long id, Translation translation);

    // search
    Page<Translation> search(String keyword, Pageable pageable);

    // filter
    Page<Translation> getByStatus(String status, Pageable pageable);

    List<Translation> getBySourceLanguage(String language);

    List<Translation> getByTargetLanguage(String language);
}