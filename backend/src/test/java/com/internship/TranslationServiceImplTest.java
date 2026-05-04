package com.internship.tool;

import com.internship.tool.entity.Translation;
import com.internship.tool.exception.ResourceNotFoundException;
import com.internship.tool.exception.ValidationException;
import com.internship.tool.exception.DuplicateResourceException;
import com.internship.tool.repository.TranslationRepository;
import com.internship.tool.service.TranslationServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TranslationServiceImplTest {

    @Mock
    private TranslationRepository translationRepository;

    @InjectMocks
    private TranslationServiceImpl translationService;

    private Translation translation;

    @BeforeEach
    void setUp() {
        translation = new Translation();
        translation.setId(1L);
        translation.setSourceText("Hello");
        translation.setTranslatedText("Hola");
        translation.setSourceLanguage("en");
        translation.setTargetLanguage("es");
        translation.setStatus("COMPLETED");
    }

    @Test
    void testCreateTranslation_Success() {
        when(translationRepository.save(any(Translation.class)))
            .thenReturn(translation);
        Translation result = translationService.create(translation);
        assertNotNull(result);
        assertEquals("Hello", result.getSourceText());
        verify(translationRepository, times(1)).save(any(Translation.class));
    }

    @Test
    void testCreateTranslation_EmptySourceText_ThrowsValidationException() {
        translation.setSourceText("");
        assertThrows(ValidationException.class,
            () -> translationService.create(translation));
    }

    @Test
    void testCreateTranslation_NullSourceText_ThrowsValidationException() {
        translation.setSourceText(null);
        assertThrows(ValidationException.class,
            () -> translationService.create(translation));
    }

    @Test
    void testCreateTranslation_EmptyTranslatedText_ThrowsValidationException() {
        translation.setTranslatedText("");
        assertThrows(ValidationException.class,
            () -> translationService.create(translation));
    }

    @Test
    void testCreateTranslation_NullTranslatedText_ThrowsValidationException() {
        translation.setTranslatedText(null);
        assertThrows(ValidationException.class,
            () -> translationService.create(translation));
    }

    @Test
    void testCreateTranslation_EmptySourceLanguage_ThrowsValidationException() {
        translation.setSourceLanguage("");
        assertThrows(ValidationException.class,
            () -> translationService.create(translation));
    }

    @Test
    void testCreateTranslation_EmptyTargetLanguage_ThrowsValidationException() {
        translation.setTargetLanguage("");
        assertThrows(ValidationException.class,
            () -> translationService.create(translation));
    }

    @Test
    void testCreateTranslation_SameLanguage_ThrowsDuplicateResourceException() {
        translation.setTargetLanguage("en");
        assertThrows(DuplicateResourceException.class,
            () -> translationService.create(translation));
    }

    @Test
    void testGetById_Success() {
        when(translationRepository.findById(1L))
            .thenReturn(Optional.of(translation));
        Translation result = translationService.getById(1L);
        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    void testGetById_NotFound_ThrowsResourceNotFoundException() {
        when(translationRepository.findById(99L))
            .thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class,
            () -> translationService.getById(99L));
    }

    @Test
    void testGetAll_ReturnsPage() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Translation> page = new PageImpl<>(List.of(translation));
        when(translationRepository.findAll(pageable)).thenReturn(page);
        Page<Translation> result = translationService.getAll(pageable);
        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
    }

    @Test
    void testDelete_Success() {
        when(translationRepository.findById(1L))
            .thenReturn(Optional.of(translation));
        doNothing().when(translationRepository).delete(translation);
        assertDoesNotThrow(() -> translationService.delete(1L));
        verify(translationRepository, times(1)).delete(translation);
    }

    @Test
    void testDelete_NotFound_ThrowsResourceNotFoundException() {
        when(translationRepository.findById(99L))
            .thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class,
            () -> translationService.delete(99L));
    }

    @Test
    void testSearch_EmptyKeyword_ThrowsValidationException() {
        assertThrows(ValidationException.class,
            () -> translationService.search("", PageRequest.of(0, 10)));
    }

    @Test
    void testSearch_NullKeyword_ThrowsValidationException() {
        assertThrows(ValidationException.class,
            () -> translationService.search(null, PageRequest.of(0, 10)));
    }

    @Test
    void testSearch_Success() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Translation> page = new PageImpl<>(List.of(translation));
        when(translationRepository.searchByKeyword("Hello", pageable))
            .thenReturn(page);
        Page<Translation> result = translationService.search("Hello", pageable);
        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
    }

    @Test
    void testUpdate_Success() {
        when(translationRepository.findById(1L))
            .thenReturn(Optional.of(translation));
        when(translationRepository.save(any(Translation.class)))
            .thenReturn(translation);
        Translation updated = new Translation();
        updated.setSourceText("Hi");
        updated.setTranslatedText("Hola");
        updated.setSourceLanguage("en");
        updated.setTargetLanguage("es");
        updated.setStatus("COMPLETED");
        Translation result = translationService.update(1L, updated);
        assertNotNull(result);
        verify(translationRepository, times(1)).save(any(Translation.class));
    }

    @Test
    void testGetBySourceLanguage_Success() {
        when(translationRepository.findBySourceLanguage("en"))
            .thenReturn(List.of(translation));
        List<Translation> result = translationService.getBySourceLanguage("en");
        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    void testGetBySourceLanguage_Empty_ThrowsValidationException() {
        assertThrows(ValidationException.class,
            () -> translationService.getBySourceLanguage(""));
    }

    @Test
    void testGetByTargetLanguage_Success() {
        when(translationRepository.findByTargetLanguage("es"))
            .thenReturn(List.of(translation));
        List<Translation> result = translationService.getByTargetLanguage("es");
        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    void testGetByStatus_Success() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Translation> page = new PageImpl<>(List.of(translation));
        when(translationRepository.findByStatus("COMPLETED", pageable))
            .thenReturn(page);
        Page<Translation> result = translationService.getByStatus("COMPLETED", pageable);
        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
    }

    @Test
    void testGetByStatus_Empty_ThrowsValidationException() {
        assertThrows(ValidationException.class,
            () -> translationService.getByStatus("", PageRequest.of(0, 10)));
    }
}