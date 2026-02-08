package com.example.back_task.service.impl;

import com.example.back_task.dto.PartyCreateDto;
import com.example.back_task.entity.Case;
import com.example.back_task.entity.Party;
import com.example.back_task.exception.CaseNotFoundException;
import com.example.back_task.repository.CaseRepository;
import com.example.back_task.repository.PartyRepository;
import com.example.back_task.service.PartyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PartyServiceImpl implements PartyService {

    private final PartyRepository partyRepository;
    private final CaseRepository caseRepository;

    @Override
    public Party create(Long caseId, PartyCreateDto dto) {

        Case caseEntity = caseRepository.findById(caseId)
                .orElseThrow(() -> new CaseNotFoundException("Case not found"));

        Party party = Party.builder()
                .type(dto.getType())
                .role(dto.getRole())
                .name(dto.getName())
                .caseEntity(caseEntity)
                .build();

        return partyRepository.save(party);
    }



    @Override
    public List<Party> getAllByCase(Long caseId) {
        return partyRepository.findAllByCaseEntityId(caseId);
    }

    @Override
    public Party getById(Long id) {
        return partyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Party not found"));
    }

    @Override
    public Party update(Long id, PartyCreateDto dto) {

        Party existing = partyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Party not found"));

        existing.setType(dto.getType());
        existing.setRole(dto.getRole());
        existing.setName(dto.getName());

        return partyRepository.save(existing);
    }


    @Override
    public void delete(Long id) {
        partyRepository.deleteById(id);
    }
}
