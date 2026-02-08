package com.example.back_task.service;

import com.example.back_task.dto.*;
import com.example.back_task.entity.Case;
import com.example.back_task.enums.CaseStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

public interface CaseService {

    @Transactional
    CaseResponseDto createCase(CaseCreateDto dto);

    CaseResponseDto getById(Long id);

    Page<CaseResponseDto> getAll(CaseStatus status, Pageable pageable);

    Page<CaseResponseDto> getByStatus(String status, Pageable pageable);

    @Transactional
    CaseResponseDto changeStatus(Long id, CaseStatusChangeDto dto);

    @Transactional
    CaseResponseDto updateStatus(Long id, CaseStatusUpdateRequestDto request);

    Page<StatusHistoryResponseDto> getHistory(Long id, int page, int size);

    Case findByCaseNumber(String caseNumber);

    Page<Case> findAllByStatus(CaseStatus status, Pageable pageable);

    Page<Case> findAll(Pageable pageable);
}

