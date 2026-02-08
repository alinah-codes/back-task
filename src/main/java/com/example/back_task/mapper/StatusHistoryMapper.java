package com.example.back_task.mapper;


import com.example.back_task.dto.StatusHistoryResponseDto;
import com.example.back_task.entity.StatusHistory;
import org.springframework.stereotype.Component;

@Component
public class StatusHistoryMapper {

    public StatusHistoryResponseDto toResponse(StatusHistory entity) {
        return StatusHistoryResponseDto.builder()
                .oldStatus(entity.getOldStatus())
                .newStatus(entity.getNewStatus())
                .changedAt(entity.getChangedAt())
                .changedBy(entity.getChangedBy())
                .build();
    }
}

