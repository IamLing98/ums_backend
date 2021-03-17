package com.linkdoan.backend.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "salary_table_contract")
public class SalaryTableContract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @Column(name = "salary_table_id")
    private Long salaryTableId;

    @Column(name = "contract_id")
    private Long contractId;

    @Column(name = "basic_salary")
    private Double basicSalary;

    @Column(name = "will_payment_salary")
    private Double willPaymentSalary;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private Integer status;

}
