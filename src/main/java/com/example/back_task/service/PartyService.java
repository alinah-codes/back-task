package com.example.back_task.service;

import com.example.back_task.dto.PartyCreateDto;
import com.example.back_task.entity.Party;

import java.util.List;

public interface PartyService {

    Party create(Long caseId, PartyCreateDto dto);

    List<Party> getAllByCase(Long caseId);

    Party getById(Long id);

    Party update(Long id, PartyCreateDto dto);

    void delete(Long id);
}
