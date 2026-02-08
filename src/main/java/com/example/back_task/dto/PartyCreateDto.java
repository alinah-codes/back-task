package com.example.back_task.dto;

import com.example.back_task.enums.PartyType;
import com.example.back_task.enums.RoleType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PartyCreateDto {

    @NotNull(message = "Party type is required")
    private PartyType type;

    @NotNull(message = "Role is required")
    private RoleType role;

    @NotBlank(message = "Name is required")
    private String name;
}

