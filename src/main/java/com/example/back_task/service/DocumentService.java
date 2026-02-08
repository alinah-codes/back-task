package com.example.back_task.service;

import com.example.back_task.dto.DocumentCreateDto;
import com.example.back_task.entity.Document;

import java.util.List;

public interface DocumentService {

    Document create(Long caseId, DocumentCreateDto dto);

    List<Document> getAllByCase(Long caseId);

    Document getById(Long id);

    Document update(Long id, DocumentCreateDto dto);

    void delete(Long id);
}
