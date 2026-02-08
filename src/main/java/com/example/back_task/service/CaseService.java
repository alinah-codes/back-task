package com.example.back_task.service;

import com.example.back_task.dto.CaseCreateDto;
import com.example.back_task.dto.CaseResponseDto;
import com.example.back_task.dto.CaseStatusChangeDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

public interface CaseService {

//    CaseResponseDto createCase(CaseCreateDto dto);
//
//    @Transactional
//    CaseResponseDto createCase(CaseCreateDto dto);

    @Transactional
    CaseResponseDto createCase(CaseCreateDto dto);

    CaseResponseDto getById(Long id);

    Page<CaseResponseDto> getAll(Pageable pageable);

    Page<CaseResponseDto> getByStatus(String status, Pageable pageable);

    @Transactional
    CaseResponseDto changeStatus(Long id, CaseStatusChangeDto dto);

//    CaseResponseDto changeStatus(Long id, CaseStatusChangeDto dto);
//
//    @Transactional
//    CaseResponseDto changeStatus(Long id, CaseStatusChangeDto dto);
}

