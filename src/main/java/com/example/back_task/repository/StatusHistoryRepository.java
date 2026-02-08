package com.example.back_task.repository;

import com.example.back_task.entity.StatusHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StatusHistoryRepository extends JpaRepository<StatusHistory, Long> {

    List<StatusHistory> findAllByCaseEntityIdOrderByChangedAtDesc(Long caseId);
}
