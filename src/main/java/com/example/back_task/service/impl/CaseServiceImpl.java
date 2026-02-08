package com.example.back_task.service.impl;

import com.example.back_task.dto.*;
import com.example.back_task.entity.Case;
import com.example.back_task.entity.StatusHistory;
import com.example.back_task.enums.CaseStatus;
import com.example.back_task.exception.BusinessException;
import com.example.back_task.exception.CaseNotFoundException;
import com.example.back_task.mapper.CaseMapper;
import com.example.back_task.process.CaseStatusMachine;
import com.example.back_task.repository.CaseRepository;
import com.example.back_task.repository.StatusHistoryRepository;
import com.example.back_task.service.CaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CaseServiceImpl implements CaseService {

    private final CaseRepository caseRepository;
    private final StatusHistoryRepository historyRepository;
    private final CaseMapper caseMapper;
    private final CaseStatusMachine statusMachine;

    @Transactional
    @Override
    public CaseResponseDto createCase(CaseCreateDto dto) {

        if (caseRepository.existsByCaseNumber(dto.getCaseNumber())) {
            throw new BusinessException("Case number already exists");
        }

        Case caseEntity = caseMapper.toEntity(dto);
        Case saved = caseRepository.save(caseEntity);

        return caseMapper.toResponse(saved);
    }

    @Override
    public CaseResponseDto getById(Long id) {

        Case caseEntity = caseRepository.findById(id)
                .orElseThrow(() -> new CaseNotFoundException("Case not found"));

        return caseMapper.toResponse(caseEntity);
    }

    @Override
    public Page<CaseResponseDto> getAll(CaseStatus status, Pageable pageable) {

        if (status != null) {
            return caseRepository.findAllByStatus(status, pageable)
                    .map(caseMapper::toResponse);
        }

        return caseRepository.findAll(pageable)
                .map(caseMapper::toResponse);
    }


    @Override
    public Page<CaseResponseDto> getByStatus(String status, Pageable pageable) {

        CaseStatus caseStatus;

        try {
            caseStatus = CaseStatus.valueOf(status.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new BusinessException("Invalid status value");
        }

        return caseRepository.findAllByStatus(caseStatus, pageable)
                .map(caseMapper::toResponse);
    }

    @Transactional
    @Override
    public CaseResponseDto changeStatus(Long id, CaseStatusChangeDto dto) {

        Case caseEntity = caseRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Case not found"));

        CaseStatus newStatus = dto.getNewStatus();

        statusMachine.validateTransition(caseEntity, newStatus);

        CaseStatus oldStatus = caseEntity.getStatus();

        caseEntity.setStatus(newStatus);

        if (newStatus == CaseStatus.COMPLETED) {
            caseEntity.setEndDate(java.time.LocalDate.now());
        }

        StatusHistory history = StatusHistory.builder()
                .caseEntity(caseEntity)
                .oldStatus(oldStatus)
                .newStatus(newStatus)
                .changedAt(LocalDateTime.now())
                .changedBy(dto.getChangedBy())
                .build();

        historyRepository.save(history);

        Case updated = caseRepository.save(caseEntity);

        return caseMapper.toResponse(updated);
    }

    @Transactional
    @Override
    public CaseResponseDto updateStatus(Long id, CaseStatusUpdateRequestDto request) {

        Case entity = caseRepository.findById(id)
                .orElseThrow(() -> new CaseNotFoundException("Case not found"));

        CaseStatus oldStatus = entity.getStatus();
        CaseStatus newStatus = request.getStatus();

        statusMachine.validateTransition(entity, newStatus);


        entity.setStatus(newStatus);

        caseRepository.save(entity);

        StatusHistory history = new StatusHistory();
        history.setCaseEntity(entity);
        history.setOldStatus(oldStatus);
        history.setNewStatus(newStatus);

        history.setChangedAt(LocalDateTime.now());

        historyRepository.save(history);

        return caseMapper.toResponse(entity);
    }

    @Override
    public Page<StatusHistoryResponseDto> getHistory(Long id, int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        return historyRepository
                .findAllByCaseEntity_Id(id, pageable)
                .map(history -> StatusHistoryResponseDto.builder()
                        .oldStatus(history.getOldStatus())
                        .newStatus(history.getNewStatus())
                        .changedBy(history.getChangedBy())
                        .changedAt(history.getChangedAt())
                        .build());
    }

    @Override
    public Case findByCaseNumber(String caseNumber) {
        return caseRepository.findByCaseNumber(caseNumber)
                .orElseThrow(() -> new CaseNotFoundException("Case not found"));
    }

    @Override
    public Page<Case> findAllByStatus(CaseStatus status, Pageable pageable) {
        return caseRepository.findAllByStatus(status, pageable);
    }

    @Override
    public Page<Case> findAll(Pageable pageable) {
        return caseRepository.findAll(pageable);
    }

}

