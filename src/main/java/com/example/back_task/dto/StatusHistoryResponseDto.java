package com.example.back_task.dto;

import com.example.back_task.enums.CaseStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class StatusHistoryResponseDto {

    private CaseStatus oldStatus;
    private CaseStatus newStatus;
    private LocalDateTime changedAt;
    private String changedBy;
}

