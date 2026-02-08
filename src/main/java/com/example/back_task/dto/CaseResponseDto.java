package com.example.back_task.dto;

import com.example.back_task.enums.CaseStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class CaseResponseDto {

    private Long id;
    private String caseNumber;
    private String procedureType;
    private CaseStatus status;
    private LocalDate startDate;
    private LocalDate endDate;

    private List<String> parties;
    private List<String> documents;
}

