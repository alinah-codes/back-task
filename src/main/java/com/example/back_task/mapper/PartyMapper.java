package com.example.back_task.mapper;

import com.example.back_task.dto.PartyCreateDto;
import com.example.back_task.dto.PartyResponseDto;
import com.example.back_task.entity.Party;
import org.springframework.stereotype.Component;

@Component
public class PartyMapper {

    public Party toEntity(PartyCreateDto dto) {
        return Party.builder()
                .type(dto.getType())
                .role(dto.getRole())
                .name(dto.getName())
                .build();
    }

    public PartyResponseDto toResponse(Party entity) {
        return PartyResponseDto.builder()
                .id(entity.getId())
                .type(entity.getType())
                .role(entity.getRole())
                .name(entity.getName())
                .build();
    }
}

