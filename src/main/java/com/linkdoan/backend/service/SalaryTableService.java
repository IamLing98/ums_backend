package com.linkdoan.backend.service;

import com.linkdoan.backend.dto.SalaryTableDTO;

import java.util.List;

public interface SalaryTableService {

    SalaryTableDTO create(SalaryTableDTO salaryTableDTO);

    List<SalaryTableDTO> getAll();
}
