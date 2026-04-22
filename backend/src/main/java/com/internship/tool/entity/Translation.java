package com.internship.tool.entity;

import jakarta.persistence.*;
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

    @Column(nullable = false)
    private String sourceText;

    @Column(nullable = false)
    private String translatedText;

    @Column(nullable = false, length = 10)
    private String sourceLanguage;

    @Column(nullable = false, length = 10)
    private String targetLanguage;

    @Column(nullable = false)
    private String status;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime updatedAt;
}