package com.example.back_task.repository;

import com.example.back_task.entity.Party;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PartyRepository extends JpaRepository<Party, Long> {

    List<Party> findAllByCaseEntityId(Long caseId);
}
