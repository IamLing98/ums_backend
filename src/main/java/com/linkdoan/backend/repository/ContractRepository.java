package com.linkdoan.backend.repository;

import com.linkdoan.backend.dto.ContractDTO;
import com.linkdoan.backend.model.BonusExactValue;
import com.linkdoan.backend.model.BonusPercentValue;
import com.linkdoan.backend.model.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ContractRepository extends JpaRepository<Contract, Long> {

    @Query(
            value = "SELECT new com.linkdoan.backend.dto.ContractDTO(contract.id, contract.employeeId, employee, contract.departmentId," +
                    " department, contract.employeeLevelId, contract.contractType, contract.salaryStructType, contract.perHourPrice, " +
                    "contract.employeeCoefficientLevelId, contract.paymentPlanId, contract.startedDate, contract.endDate, employeeLevel,ECL) " +
                    "FROM Contract contract INNER JOIN Employee employee ON contract.employeeId = employee.employeeId " +
                    "INNER JOIN Department department ON contract.departmentId = department.departmentId " +
                    "INNER JOIN EmployeeLevel employeeLevel ON contract.employeeLevelId = employeeLevel.id " +
                    "INNER JOIN EmployeeCoefficientLevel ECL ON contract.employeeCoefficientLevelId = ECL.id "
    )
    List<ContractDTO> getAll();

    @Query(
            value = "SELECT new com.linkdoan.backend.dto.ContractDTO(contract.id, contract.employeeId, employee, contract.departmentId," +
                    " department, contract.employeeLevelId, contract.contractType, contract.salaryStructType, contract.perHourPrice, " +
                    "contract.employeeCoefficientLevelId, contract.paymentPlanId, contract.startedDate, contract.endDate, employeeLevel, ECL) " +
                    "FROM Contract contract INNER JOIN Employee employee ON contract.employeeId = employee.employeeId " +
                    "INNER JOIN Department department ON contract.departmentId = department.departmentId " +
                    "INNER JOIN EmployeeLevel employeeLevel ON contract.employeeLevelId = employeeLevel.id " +
                    "INNER JOIN SalaryTableContract STC ON STC.contractId = contract.id " +
                    "INNER JOIN EmployeeCoefficientLevel ECL ON contract.employeeCoefficientLevelId = ECL.id " +
                    "WHERE STC.id = :salaryTableContractId"
    )
    ContractDTO getAllBySalaryTableContractId(@Param("salaryTableContractId") Long salaryTableContractId);

    @Query(
            value = "SELECT bonusExactValue " +
                    "FROM Contract contract INNER JOIN ContractExactBonus contractExactBonus ON contract.id = contractExactBonus.contractId " +
                    "INNER JOIN BonusExactValue bonusExactValue ON contractExactBonus.bonusId = bonusExactValue.id"
    )
    List<BonusExactValue> findAllBonusExactValueListByContractId(@Param("contractId") Long contractId);

    @Query(
            value = "SELECT bonusExactValue " +
                    "FROM Contract contract INNER JOIN ContractPercentBonus contractPercentBonus ON contract.id = contractPercentBonus.contractId " +
                    "INNER JOIN BonusPercentValue bonusExactValue ON contractPercentBonus.bonusId = bonusExactValue.id"
    )
    List<BonusPercentValue> findAllBonusPercentValueListByContractId(@Param("contractId") Long contractId);


}
