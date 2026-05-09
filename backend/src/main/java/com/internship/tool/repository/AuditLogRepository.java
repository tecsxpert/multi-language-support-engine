package com.internship.tool.repository;

import com.internship.tool.entity.AuditLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {
    List<AuditLog> findByEntityName(String entityName);
    Page<AuditLog> findByPerformedBy(String performedBy, Pageable pageable);
    List<AuditLog> findTop10ByOrderByCreatedAtDesc();
}