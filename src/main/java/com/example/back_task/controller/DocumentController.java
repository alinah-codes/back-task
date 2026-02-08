package com.example.back_task.controller;


import com.example.back_task.dto.DocumentCreateDto;
import com.example.back_task.entity.Document;
import com.example.back_task.service.DocumentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/documents")
@RequiredArgsConstructor
public class DocumentController {

    private final DocumentService documentService;

    @PostMapping("/cases/{caseId}/documents")
    public ResponseEntity<Document> create(
            @PathVariable Long caseId,
            @Valid @RequestBody DocumentCreateDto dto
    ) {
        return ResponseEntity.ok(documentService.create(caseId, dto));
    }


    @GetMapping("/case/{caseId}")
    public List<Document> getAllByCase(@PathVariable Long caseId) {
        return documentService.getAllByCase(caseId);
    }

    @GetMapping("/{id}")
    public Document getById(@PathVariable Long id) {
        return documentService.getById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Document> update(
            @PathVariable Long id,
            @RequestBody @Valid DocumentCreateDto dto
    ) {
        return ResponseEntity.ok(documentService.update(id, dto));
    }


    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        documentService.delete(id);
    }
}

