package com.linkdoan.backend.dto;

import com.linkdoan.backend.model.Contract;
import com.linkdoan.backend.model.SalaryTable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalaryTableDTO {

    private Long id;

    private String salaryTableName;

    private LocalDate startedDate;

    private LocalDate endDate;

    private Integer status;

    private Double totalValue;

    private List<ContractDTO> contractDTOList;

    private List<SalaryTableContractDTO> salaryTableContractDTOS;

    public SalaryTable toModel() {
        SalaryTable salaryTable = new SalaryTable();
        salaryTable.setId(id);
        salaryTable.setSalaryTableName(salaryTableName);
        salaryTable.setStartedDate(startedDate);
        salaryTable.setEndDate(endDate);
        salaryTable.setStatus(status);
        salaryTable.setTotalValue(totalValue);
        return salaryTable;
    }
}
