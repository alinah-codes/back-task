package com.example.back_task.repository;

import com.example.back_task.entity.Case;
import com.example.back_task.enums.CaseStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CaseRepository extends JpaRepository<Case, Long> {

    Page<Case> findAllByStatus(CaseStatus status, Pageable pageable);
    boolean existsByCaseNumber(String caseNumber);
    List<Case> findAllByStatusAndDeadlineBefore(
            CaseStatus status,
            LocalDate deadline
    );
    Optional<Case> findByCaseNumber(String caseNumber);



}

