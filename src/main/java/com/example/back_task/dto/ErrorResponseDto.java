package com.example.back_task.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class ErrorResponseDto {

    private String message;
    private String error;
    private int status;
    private LocalDateTime timestamp;
}
