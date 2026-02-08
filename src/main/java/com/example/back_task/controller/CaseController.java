package com.example.back_task.controller;

import com.example.back_task.dto.*;
import com.example.back_task.entity.Case;
import com.example.back_task.enums.CaseStatus;
import com.example.back_task.service.CaseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cases")
@RequiredArgsConstructor
public class CaseController {

    private final CaseService caseService;

    @GetMapping
    public Page<Case> getAll(
            @RequestParam(required = false) CaseStatus status,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);

        if (status != null) {
            return caseService.findAllByStatus(status, pageable);
        }

        return caseService.findAll(pageable);
    }



    @PostMapping
    public CaseResponseDto create(@Valid @RequestBody CaseCreateDto request) {
        return caseService.createCase(request);
    }

    @GetMapping("/{id}")
    public CaseResponseDto getById(@PathVariable Long id) {
        return caseService.getById(id);
    }


    @PatchMapping("/{id}/status")
    public CaseResponseDto updateStatus(
            @PathVariable Long id,
            @Valid @RequestBody CaseStatusChangeDto request
    ) {
        return caseService.changeStatus(id, request);
    }

    @GetMapping("/{id}/history")
    public Page<StatusHistoryResponseDto> getHistory(
            @PathVariable Long id,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return caseService.getHistory(id, page, size);
    }
    @GetMapping("/search")
    public Case findByCaseNumber(@RequestParam String caseNumber) {
        return caseService.findByCaseNumber(caseNumber);
    }

}

