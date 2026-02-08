package com.example.back_task.service.impl;

import com.example.back_task.entity.Case;
import com.example.back_task.entity.StatusHistory;
import com.example.back_task.enums.CaseStatus;
import com.example.back_task.repository.CaseRepository;
import com.example.back_task.repository.StatusHistoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CaseSchedulerService {

    private final CaseRepository caseRepository;
    private final StatusHistoryRepository historyRepository;

    @Scheduled(cron = "0 0 1 * * ?")
    @Transactional
    public void moveExpiredCasesToOverdue() {

        log.info(" Checking expired cases...");

        List<Case> expiredCases =
                caseRepository.findAllByStatusAndDeadlineBefore(
                        CaseStatus.IN_PROGRESS,
                        LocalDate.now()
                );

        for (Case caseEntity : expiredCases) {

            CaseStatus oldStatus = caseEntity.getStatus();
            caseEntity.setStatus(CaseStatus.OVERDUE);

            StatusHistory history = StatusHistory.builder()
                    .caseEntity(caseEntity)
                    .oldStatus(oldStatus)
                    .newStatus(CaseStatus.OVERDUE)
                    .changedAt(LocalDateTime.now())
                    .changedBy("SYSTEM")
                    .build();

            historyRepository.save(history);
        }

        log.info(" Expired cases updated: {}", expiredCases.size());
    }
}

