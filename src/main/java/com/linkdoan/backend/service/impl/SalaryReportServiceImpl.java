package com.linkdoan.backend.service.impl;

import com.linkdoan.backend.dto.ContractDTO;
import com.linkdoan.backend.dto.SalaryTableContractDTO;
import com.linkdoan.backend.model.Contract;
import com.linkdoan.backend.model.SalaryTableContract;
import com.linkdoan.backend.repository.ContractRepository;
import com.linkdoan.backend.repository.SalaryTableContractRepository;
import com.linkdoan.backend.service.SalaryReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalaryReportServiceImpl implements SalaryReportService {

    @Autowired
    SalaryTableContractRepository salaryTableContractRepository;

    @Autowired
    ContractRepository contractRepository;

    @Override
    public List<SalaryTableContractDTO> getAll() {
        List<SalaryTableContractDTO> salaryTableContractList = salaryTableContractRepository.getAll();
        for(SalaryTableContractDTO salaryTableContractDTO : salaryTableContractList){
            ContractDTO contractDTOS = contractRepository.getAllBySalaryTableContractId(salaryTableContractDTO.getId());
            salaryTableContractDTO.setContractDTO(contractDTOS);
        }
        return salaryTableContractList;
    }

    @Override
    public SalaryTableContractDTO create(SalaryTableContractDTO salaryTableContractDTO) {
        return null;
    }
}
