package com.example.back_task.process;

import com.example.back_task.entity.Case;
import com.example.back_task.enums.CaseStatus;
import com.example.back_task.exception.BusinessException;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Map;
import java.util.Set;

@Component
public class CaseStatusMachine {

    private final Map<CaseStatus, Set<CaseStatus>> allowedTransitions = new EnumMap<>(CaseStatus.class);

    public CaseStatusMachine() {
        allowedTransitions.put(CaseStatus.CREATED,
                EnumSet.of(CaseStatus.IN_PROGRESS));

        allowedTransitions.put(CaseStatus.IN_PROGRESS,
                EnumSet.of(CaseStatus.PROCEDURE_RUNNING));

        allowedTransitions.put(CaseStatus.PROCEDURE_RUNNING,
                EnumSet.of(CaseStatus.COMPLETED));

        allowedTransitions.put(CaseStatus.COMPLETED,
                EnumSet.of(CaseStatus.ARCHIVED));

        allowedTransitions.put(CaseStatus.ARCHIVED,
                EnumSet.noneOf(CaseStatus.class));
    }

    public void validateTransition(Case caseEntity, CaseStatus newStatus) {

        CaseStatus currentStatus = caseEntity.getStatus();

        if (currentStatus == newStatus) {
            throw new BusinessException("Case is already in status: " + newStatus);
        }

        Set<CaseStatus> allowed = allowedTransitions.get(currentStatus);

        if (allowed == null || !allowed.contains(newStatus)) {
            throw new BusinessException(
                    String.format("Transition from %s to %s is not allowed",
                            currentStatus, newStatus)
            );
        }

        validateBusinessRules(caseEntity, newStatus);
    }

    private void validateBusinessRules(Case caseEntity, CaseStatus newStatus) {



        if (newStatus == CaseStatus.COMPLETED &&
                (caseEntity.getDocuments() == null || caseEntity.getDocuments().isEmpty())) {

            throw new BusinessException("Cannot complete case without documents");
        }

        if (newStatus == CaseStatus.ARCHIVED &&
                caseEntity.getStatus() != CaseStatus.COMPLETED) {

            throw new BusinessException("Only completed cases can be archived");
        }
    }
}

