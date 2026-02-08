package com.example.back_task.repository;

import com.example.back_task.entity.StatusHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface StatusHistoryRepository extends JpaRepository<StatusHistory, Long> {

    Page<StatusHistory> findAllByCaseEntity_Id(Long id, Pageable pageable);


}
