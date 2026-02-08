package com.example.back_task.controller;

import com.example.back_task.dto.PartyCreateDto;
import com.example.back_task.entity.Party;
import com.example.back_task.service.PartyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PartyController {

    private final PartyService partyService;

    @PostMapping("/cases/{caseId}/parties")
    public ResponseEntity<Party> create(
            @PathVariable Long caseId,
            @Valid @RequestBody PartyCreateDto dto
    ) {
        return ResponseEntity.ok(partyService.create(caseId, dto));
    }

    @GetMapping("/cases/{caseId}/parties")
    public List<Party> getAllByCase(@PathVariable Long caseId) {
        return partyService.getAllByCase(caseId);
    }

    @GetMapping("/parties/{id}")
    public Party getById(@PathVariable Long id) {
        return partyService.getById(id);
    }

    @PutMapping("/parties/{id}")
    public ResponseEntity<Party> update(
            @PathVariable Long id,
            @RequestBody @Valid PartyCreateDto dto
    ) {
        return ResponseEntity.ok(partyService.update(id, dto));
    }


    @DeleteMapping("/parties/{id}")
    public void delete(@PathVariable Long id) {
        partyService.delete(id);
    }
}

