package com.linkdoan.backend.dto;

import com.linkdoan.backend.model.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContractDTO {

    private Long id;

    private String employeeId;

    private Employee employee;

    private String departmentId;

    private Department department;

    private Integer employeeLevelId;

    private EmployeeLevel employeeLevel;

    private Integer contractType;

    private Integer salaryStructType;

    private Double perHourPrice;

    private Long employeeCoefficientLevelId;

    private EmployeeCoefficientLevel employeeCoefficientLevel;

    private Double paymentPlanId;

    private LocalDate startedDate;

    private LocalDate endDate;

    private String note;

    private Integer status;

    private List<ContractExactBonus> contractExactBonusList;

    private List<ContractPercentBonus> contractPercentBonusList;

    public ContractDTO(Long id, String employeeId, Employee employee, String departmentId, Department department,
                       Integer employeeLevelId, Integer contractType, Integer salaryStructType, Double perHourPrice,
                       Long employeeCoefficientLevelId, Double paymentPlanId, LocalDate startedDate, LocalDate endDate,
                       EmployeeLevel employeeLevel, EmployeeCoefficientLevel employeeCoefficientLevel) {
        this.id = id;
        this.employeeId = employeeId;
        this.employee = employee;
        this.departmentId = departmentId;
        this.department = department;
        this.employeeLevelId = employeeLevelId;
        this.contractType = contractType;
        this.salaryStructType = salaryStructType;
        this.perHourPrice = perHourPrice;
        this.employeeCoefficientLevelId = employeeCoefficientLevelId;
        this.paymentPlanId = paymentPlanId;
        this.startedDate = startedDate;
        this.endDate = endDate;
        this.employeeLevel = employeeLevel;
        this.employeeCoefficientLevel = employeeCoefficientLevel;
    }

    public Contract toModel() {
        Contract newContract = new Contract();
        newContract.setId(id);
        newContract.setEmployeeId(employeeId);
        newContract.setDepartmentId(departmentId);
        newContract.setEmployeeLevelId(employeeLevelId);
        newContract.setContractType(contractType);
        newContract.setSalaryStructType(salaryStructType);
        newContract.setPerHourPrice(perHourPrice);
        newContract.setEmployeeCoefficientLevelId(employeeCoefficientLevelId);
        newContract.setPaymentPlanId(paymentPlanId);
        newContract.setStartedDate(startedDate);
        newContract.setEndDate(endDate);
        newContract.setNote(note);
        return newContract;
    }
}
