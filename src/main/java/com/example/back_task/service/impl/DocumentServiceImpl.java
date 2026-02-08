package com.example.back_task.service.impl;


import com.example.back_task.dto.DocumentCreateDto;
import com.example.back_task.entity.Case;
import com.example.back_task.entity.Document;
import com.example.back_task.repository.CaseRepository;
import com.example.back_task.repository.DocumentRepository;
import com.example.back_task.service.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DocumentServiceImpl implements DocumentService {

    private final DocumentRepository documentRepository;
    private final CaseRepository caseRepository;

    @Override
    public Document create(Long caseId, DocumentCreateDto dto) {

        Case caseEntity = caseRepository.findById(caseId)
                .orElseThrow(() -> new RuntimeException("Case not found"));

        Document document = new Document();
        document.setName(dto.getName());
        document.setType(dto.getType());
        document.setCaseEntity(caseEntity);

        return documentRepository.save(document);
    }


    @Override
    public List<Document> getAllByCase(Long caseId) {
        return documentRepository.findAllByCaseEntityId(caseId);
    }

    @Override
    public Document getById(Long id) {
        return documentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Document not found"));
    }

    @Override
    public Document update(Long id, DocumentCreateDto dto) {

        Document existing = documentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Document not found"));

        existing.setName(dto.getName());
        existing.setType(dto.getType());

        return documentRepository.save(existing);
    }


    @Override
    public void delete(Long id) {
        documentRepository.deleteById(id);
    }
}

