package com.example.back_task.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class DocumentCreateDto {

    @NotBlank(message = "Document name is required")
    private String name;

    private String type;
}

