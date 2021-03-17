package com.linkdoan.backend.service;

import com.linkdoan.backend.dto.SalaryTableDTO;
import com.linkdoan.backend.model.SalaryTable;

import java.util.List;

public interface SalaryTableService {

    SalaryTableDTO create (SalaryTableDTO salaryTableDTO);

    List<SalaryTableDTO> getAll();
}
