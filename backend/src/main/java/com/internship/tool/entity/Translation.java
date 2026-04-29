package com.internship.tool.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "translations")
@EntityListeners(AuditingEntityListener.class)
public class Translation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Source text cannot be blank")
    @Column(nullable = false)
    private String sourceText;

    @NotBlank(message = "Translated text cannot be blank")
    @Column(nullable = false)
    private String translatedText;

    @NotBlank(message = "Source language cannot be blank")
    @Size(max = 10, message = "Source language code max 10 characters")
    @Column(nullable = false, length = 10)
    private String sourceLanguage;

    @NotBlank(message = "Target language cannot be blank")
    @Size(max = 10, message = "Target language code max 10 characters")
    @Column(nullable = false, length = 10)
    private String targetLanguage;

    @NotBlank(message = "Status cannot be blank")
    @Column(nullable = false)
    private String status;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime updatedAt;
}