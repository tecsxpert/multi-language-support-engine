package com.internship.tool.service;

import com.internship.tool.entity.Translation;
import com.internship.tool.repository.TranslationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TranslationServiceImpl implements TranslationService {

    private final TranslationRepository translationRepository;

    @Override
    public Translation create(Translation translation) {
        log.info("Creating translation from {} to {}",
                translation.getSourceLanguage(),
                translation.getTargetLanguage());

        return translationRepository.save(translation);
    }

    @Override
    public Translation getById(Long id) {
        return translationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Translation not found"));
    }

    @Override
    public List<Translation> getAll() {
        return translationRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        translationRepository.deleteById(id);
    }

    // ------------------ NEW METHODS ------------------

    @Override
    public Page<Translation> getAll(Pageable pageable) {
        return translationRepository.findAll(pageable);
    }

    @Override
    public Translation update(Long id, Translation translation) {
        Translation existing = this.getById(id);

        existing.setSourceText(translation.getSourceText());
        existing.setTranslatedText(translation.getTranslatedText());
        existing.setSourceLanguage(translation.getSourceLanguage());
        existing.setTargetLanguage(translation.getTargetLanguage());
        existing.setStatus(translation.getStatus());

        return translationRepository.save(existing);
    }

    @Override
    public Page<Translation> search(String keyword, Pageable pageable) {
        return translationRepository.findBySourceTextContainingIgnoreCase(keyword, pageable);
    }

    @Override
    public Page<Translation> getByStatus(String status, Pageable pageable) {
        return translationRepository.findByStatus(status, pageable);
    }

    @Override
    public List<Translation> getBySourceLanguage(String language) {
        return translationRepository.findBySourceLanguage(language);
    }

    @Override
    public List<Translation> getByTargetLanguage(String language) {
        return translationRepository.findByTargetLanguage(language);
    }
}