package com.linkdoan.backend.service;

import com.linkdoan.backend.dto.ContractDTO;

import java.util.List;

public interface ContractService {

    ContractDTO create(ContractDTO contractDTO);

    List<ContractDTO> getAll();

    ContractDTO update(ContractDTO contractDTO);
}
