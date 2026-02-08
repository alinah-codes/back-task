package com.example.back_task.mapper;

import com.example.back_task.dto.DocumentCreateDto;
import com.example.back_task.dto.DocumentResponseDto;
import com.example.back_task.entity.Document;
import org.springframework.stereotype.Component;

@Component
public class DocumentMapper {

    public Document toEntity(DocumentCreateDto dto) {
        return Document.builder()
                .name(dto.getName())
                .type(dto.getType())
                .build();
    }

    public DocumentResponseDto toResponse(Document entity) {
        return DocumentResponseDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .type(entity.getType())
                .build();
    }
}

