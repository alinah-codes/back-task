package com.example.back_task.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CaseCreateDto {

    @NotBlank(message = "Case number is required")
    @Size(max = 50)
    private String caseNumber;

    @NotBlank(message = "Procedure type is required")
    private String procedureType;

    @NotNull(message = "Start date is required")
    private LocalDate startDate;
}
