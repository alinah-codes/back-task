package com.example.back_task.mapper;

import com.example.back_task.dto.CaseCreateDto;
import com.example.back_task.dto.CaseResponseDto;
import com.example.back_task.entity.Case;
import com.example.back_task.enums.CaseStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CaseMapper {

    private final PartyMapper partyMapper;
    private final DocumentMapper documentMapper;

    public Case toEntity(CaseCreateDto dto) {
        return Case.builder()
                .caseNumber(dto.getCaseNumber())
                .procedureType(dto.getProcedureType())
                .status(CaseStatus.CREATED)
                .startDate(dto.getStartDate())
                .build();
    }

    public CaseResponseDto toResponse(Case entity) {
        return CaseResponseDto.builder()
                .id(entity.getId())
                .caseNumber(entity.getCaseNumber())
                .procedureType(entity.getProcedureType())
                .status(entity.getStatus())
                .startDate(entity.getStartDate())
                .endDate(entity.getEndDate())
                .parties(
                        entity.getParties().stream()
                                .map(p -> p.getName())
                                .collect(Collectors.toList())
                )
                .documents(
                        entity.getDocuments().stream()
                                .map(d -> d.getName())
                                .collect(Collectors.toList())
                )
                .build();
    }
}

