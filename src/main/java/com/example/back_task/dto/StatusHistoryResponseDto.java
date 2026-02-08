package com.example.back_task.dto;

import com.example.back_task.enums.CaseStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StatusHistoryResponseDto {

    private CaseStatus oldStatus;
    private CaseStatus newStatus;
    private String changedBy;
    private LocalDateTime changedAt;
}
