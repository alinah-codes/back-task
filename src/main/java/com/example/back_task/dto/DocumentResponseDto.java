package com.example.back_task.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DocumentResponseDto {

    private Long id;
    private String name;
    private String type;
}

