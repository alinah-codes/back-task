package com.example.back_task.dto;

import com.example.back_task.enums.CaseStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Schema(description = "Request for changing case status")
public class CaseStatusChangeDto {

    @Schema(description = "New status of the case",
            example = "IN_PROGRESS",
            requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    private CaseStatus newStatus;

    @Schema(description = "User who changed the status",
            example = "admin",
            requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank
    private String changedBy;
}

