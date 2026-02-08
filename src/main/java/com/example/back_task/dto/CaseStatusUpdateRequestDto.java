package com.example.back_task.dto;

import com.example.back_task.enums.CaseStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CaseStatusUpdateRequestDto {

    @NotNull(message = "Status is required")
    private CaseStatus status;

    private String comment;

}

