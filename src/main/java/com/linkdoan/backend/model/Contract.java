package com.linkdoan.backend.model;

import com.linkdoan.backend.dto.ContractDTO;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Table(name = "contract")
@Entity
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "employee_id")
    private String employeeId;

    @Column(name = "department_id")
    private String departmentId;

    @Column(name = "employee_level_id")
    private Integer employeeLevelId;

    @Column(name = "contract_type")
    private Integer contractType;

    @Column(name = "salary_struct_type")
    private Integer salaryStructType;

    @Column(name = "per_hour_price")
    private Double perHourPrice;

    @Column(name = "employee_coefficient_level_id")
    private Long employeeCoefficientLevelId;

    @Column(name = "payment_plan_id")
    private Double paymentPlanId;

    @Column(name = "started_date")
    private LocalDate startedDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "note")
    private String note;

    @Column(name = "basic_salary")
    private Double basicSalary;

    @Column(name = "status")
    private Integer status;

    public ContractDTO toDTO() {
        ContractDTO newContract = new ContractDTO();
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
        newContract.setStatus(status);
        return newContract;
    }
}
