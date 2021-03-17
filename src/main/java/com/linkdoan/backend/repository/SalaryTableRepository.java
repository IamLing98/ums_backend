package com.linkdoan.backend.repository;

import com.linkdoan.backend.model.EmployeeCoefficientLevel;
import com.linkdoan.backend.model.SalaryTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SalaryTableRepository extends JpaRepository<SalaryTable, Long> {


    @Query(
            value = "SELECT employeeCoefficientLevel " +
                    "FROM  EmployeeCoefficientLevel employeeCoefficientLevel " +
                    "WHERE employeeCoefficientLevel.id = :employeeCoefficientLevelId "
    )
    EmployeeCoefficientLevel findByEmployeeCoefficientLevelId(@Param("employeeCoefficientLevelId") Long employeeCoefficientLevelId);


}
