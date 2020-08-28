package com.linkdoan.backend.repository;

import com.linkdoan.backend.model.Admissions;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(rollbackFor = Exception.class)
public interface AdmissionRepository {
    @Query(value = "SELECT * FROM admissions ad   WHERE (status = 1)"
            + "ORDER BY admission_id ASC ",
            nativeQuery = true
    )
    Page<Admissions> getAllActiveAdmission();
}
