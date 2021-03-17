package com.linkdoan.backend.service.impl;

import com.linkdoan.backend.dto.ContractDTO;
import com.linkdoan.backend.model.Contract;
import com.linkdoan.backend.model.ContractExactBonus;
import com.linkdoan.backend.model.ContractPercentBonus;
import com.linkdoan.backend.repository.ContractExactBonusRepository;
import com.linkdoan.backend.repository.ContractPercentBonusRepository;
import com.linkdoan.backend.repository.ContractRepository;
import com.linkdoan.backend.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
        (
                propagation = Propagation.REQUIRED,
                readOnly = false,
                rollbackFor = Throwable.class
        )
public class ContractServiceImpl implements ContractService {

    @Autowired
    ContractRepository contractRepository;

    @Autowired
    ContractExactBonusRepository contractExactBonusRepository;

    @Autowired
    ContractPercentBonusRepository contractPercentBonusRepository;

    @Override
    public ContractDTO create(ContractDTO contractDTO) {
        Contract contract = contractDTO.toModel();
        contractRepository.save(contract);
        List<ContractExactBonus> contractExactBonusList = contractDTO.getContractExactBonusList().stream().map(contractExactBonus -> {
            contractExactBonus.setContractId(contract.getId());
            return contractExactBonus;
        }).collect(Collectors.toList());
        contractExactBonusRepository.saveAll(contractExactBonusList);
        List<ContractPercentBonus> contractPercentBonusList = contractDTO.getContractPercentBonusList().stream().map(contractPercentBonus -> {
            contractPercentBonus.setContractId(contract.getId());
            return contractPercentBonus;
        }).collect(Collectors.toList());
        contractPercentBonusRepository.saveAll(contractPercentBonusList);
        return contractDTO;
    }

    @Override
    public List<ContractDTO> getAll() {
        List<ContractDTO> contractList = contractRepository.getAll();
        return contractList;
    }

    @Override
    public ContractDTO update(ContractDTO contractDTO) {
        Optional<Contract> contractOptional = contractRepository.findById(contractDTO.getId());
        if (!contractOptional.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Hợp đồng không tồn tại");
        Contract contract = contractOptional.get();

        return null;
    }

}
