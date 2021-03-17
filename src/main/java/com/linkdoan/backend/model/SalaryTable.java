package com.linkdoan.backend.model;

import com.linkdoan.backend.dto.SalaryTableDTO;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "salary_table")
public class SalaryTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "salary_table_name")
    private String salaryTableName;

    @Column(name = "started_date")
    private LocalDate startedDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name="totalValue")
    private Double totalValue;

    @Column(name = "status")
    private Integer status;

    public SalaryTableDTO toDTO() {
        SalaryTableDTO salaryTable = new SalaryTableDTO();
        salaryTable.setId(id);
        salaryTable.setSalaryTableName(salaryTableName);
        salaryTable.setStartedDate(startedDate);
        salaryTable.setEndDate(endDate);
        salaryTable.setStatus(status);
        salaryTable.setTotalValue(totalValue);
        return salaryTable;
    }
}
