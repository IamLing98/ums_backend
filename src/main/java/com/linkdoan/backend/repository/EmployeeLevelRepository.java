package com.linkdoan.backend.repository;

import com.linkdoan.backend.model.EmployeeCoefficientLevel;
import com.linkdoan.backend.model.EmployeeLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeLevelRepository extends JpaRepository<EmployeeLevel, Long> {

    @Query(
            value="SELECT ECL " +
                    "FROM EmployeeLevel EL INNER JOIN EmployeeCoefficientLevel ECL ON EL.id = ECL.employeeLevelId " +
                    "WHERE ECL.employeeLevelId = :employeeLevelId"
    )
    List<EmployeeCoefficientLevel> findAllEmployeeCoefficientLevelByEmployeeLevelId(@Param("employeeLevelId") Long employeeLevelId);
}
