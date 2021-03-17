package com.linkdoan.backend.service;

import com.linkdoan.backend.dto.SalaryTableContractDTO;
import com.linkdoan.backend.model.SalaryTableContract;

import java.util.List;

public interface SalaryReportService {

    List<SalaryTableContract> getAll();

    SalaryTableContractDTO create(SalaryTableContractDTO salaryTableContractDTO);
}
