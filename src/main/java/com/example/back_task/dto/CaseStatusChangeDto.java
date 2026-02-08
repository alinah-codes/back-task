package com.example.back_task.dto;

import com.example.back_task.enums.CaseStatus;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CaseStatusChangeDto {

    @NotNull(message = "New status is required")
    private CaseStatus newStatus;

    @NotBlank(message = "ChangedBy is required")
    private String changedBy;
}

