package com.example.back_task.controller;

import com.example.back_task.dto.CaseCreateDto;
import com.example.back_task.dto.CaseResponseDto;
import com.example.back_task.dto.CaseStatusUpdateRequestDto;
import com.example.back_task.dto.StatusHistoryResponseDto;
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

    @PostMapping
    public CaseResponseDto create(@Valid @RequestBody CaseCreateDto request) {
        return caseService.createCase(request);
    }

    @GetMapping("/{id}")
    public CaseResponseDto getById(@PathVariable Long id) {
        return caseService.getById(id);
    }

    @GetMapping
    public Page<CaseResponseDto> getAll(
            @RequestParam(required = false) CaseStatus status,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return caseService.getAll(status, pageable);
    }

    @PatchMapping("/{id}/status")
    public CaseResponseDto updateStatus(
            @PathVariable Long id,
            @Valid @RequestBody CaseStatusUpdateRequestDto request
    ) {
        return caseService.updateStatus(id, request);
    }

    @GetMapping("/{id}/history")
    public Page<StatusHistoryResponseDto> getHistory(
            @PathVariable Long id,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return caseService.getHistory(id, page, size);
    }

}

