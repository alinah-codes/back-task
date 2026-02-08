package com.example.back_task.dto;

import com.example.back_task.enums.PartyType;
import com.example.back_task.enums.RoleType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PartyResponseDto {

    private Long id;
    private PartyType type;
    private RoleType role;
    private String name;
}

