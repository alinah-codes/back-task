package com.example.back_task.repository;

import com.example.back_task.entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DocumentRepository extends JpaRepository<Document, Long> {

    List<Document> findAllByCaseEntityId(Long caseId);
}
