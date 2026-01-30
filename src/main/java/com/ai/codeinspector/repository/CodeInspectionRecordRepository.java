package com.ai.codeinspector.repository;

import com.ai.codeinspector.entity.CodeInspectionRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CodeInspectionRecordRepository extends JpaRepository<CodeInspectionRecord,Long> {
}
