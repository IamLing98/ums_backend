package com.linkdoan.backend.repository;

import com.linkdoan.backend.dto.SalaryTableContractDTO;
import com.linkdoan.backend.model.SalaryTableContract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SalaryTableContractRepository extends JpaRepository<SalaryTableContract, Long> {

    @Query(
            value = "SELECT new com.linkdoan.backend.dto.SalaryTableContractDTO(STC.id, STC.salaryTableId, STC.contractId, STC.basicSalary," +
                    " STC.willPaymentSalary, STC.description, STC.status)" +
                    "FROM SalaryTable ST INNER JOIN SalaryTableContract STC ON ST.id = STC.salaryTableId " +
                    "INNER JOIN Contract C ON STC.contractId = C.id " +
                    "WHERE STC.salaryTableId = :salaryTableId "
    )
    List<SalaryTableContractDTO> getAllBySalaryTableId(@Param("salaryTableId") Long salaryTableId);

    @Query(
            value = "SELECT new com.linkdoan.backend.dto.SalaryTableContractDTO(STC.id, STC.salaryTableId, STC.contractId, STC.basicSalary," +
                    " STC.willPaymentSalary, STC.description,ST,STC.status)" +
                    "FROM SalaryTable ST INNER JOIN SalaryTableContract STC ON ST.id = STC.salaryTableId " +
                    "INNER JOIN Contract C ON STC.contractId = C.id "
    )
    List<SalaryTableContractDTO> getAll();
}
