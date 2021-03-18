package com.linkdoan.backend.service.impl;

import com.linkdoan.backend.dto.ContractDTO;
import com.linkdoan.backend.dto.SalaryTableContractDTO;
import com.linkdoan.backend.dto.SalaryTableDTO;
import com.linkdoan.backend.model.*;
import com.linkdoan.backend.repository.ContractRepository;
import com.linkdoan.backend.repository.SalaryTableContractRepository;
import com.linkdoan.backend.repository.SalaryTableRepository;
import com.linkdoan.backend.service.SalaryTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
public class SalaryTableServiceImpl implements SalaryTableService {

    private final Double BASIC_SALARY = 1600000D;

    private final Double INSURANCE_VALUE = 0.15;

    @Autowired
    SalaryTableRepository salaryTableRepository;

    @Autowired
    SalaryTableContractRepository salaryTableContractRepository;

    @Autowired
    ContractRepository contractRepository;


    @Override
    public List<SalaryTableDTO> getAll() {
        List<SalaryTable> salaryTables = salaryTableRepository.findAll();
        List<SalaryTableDTO> salaryTableDTOList = salaryTables.stream().map(salaryTable -> {
            SalaryTableDTO salaryTableDTO = salaryTable.toDTO();
            List<SalaryTableContractDTO> salaryTableContractDTOList = salaryTableContractRepository.getAllBySalaryTableId(salaryTable.getId());
            salaryTableContractDTOList = salaryTableContractDTOList.stream().map(salaryTableContractDTO -> {
                ContractDTO contractDTOS = contractRepository.getAllBySalaryTableContractId(salaryTableContractDTO.getId());
                salaryTableContractDTO.setContractDTO(contractDTOS);
                return salaryTableContractDTO;
            }).collect(Collectors.toList());
            salaryTableDTO.setSalaryTableContractDTOS(salaryTableContractDTOList);
            return salaryTableDTO;
        }).collect(Collectors.toList());
        return salaryTableDTOList;
    }

    @Override
    public SalaryTableDTO create(SalaryTableDTO salaryTableDTO) {
        salaryTableDTO.setStatus(0);
        SalaryTable salaryTable =
                salaryTableRepository.save(salaryTableDTO.toModel());
        AtomicReference<Double> totalValueOfSalaryTable = new AtomicReference<>(0D);
        List<ContractDTO> contractDTOS = salaryTableDTO.getContractDTOList();
        if (contractDTOS != null) {
            List<SalaryTableContract> salaryTableContractList = contractDTOS.stream().map(contractDTO -> {
                Optional<Contract> contractOptional = contractRepository.findById(contractDTO.getId());
                if (contractOptional.isPresent()) {
                    String description = "";
                    Contract contract = contractOptional.get();
                    EmployeeCoefficientLevel employeeCoefficientLevel = salaryTableRepository.findByEmployeeCoefficientLevelId(contract.getEmployeeCoefficientLevelId());
                    if (employeeCoefficientLevel == null)
                        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "hỆ SỐ LƯƠNG KHÔNG HỢP LỆ");
                    List<BonusExactValue> bonusExactValueList = contractRepository.findAllBonusExactValueListByContractId(contract.getId());
                    List<BonusPercentValue> bonusPercentValueList = contractRepository.findAllBonusPercentValueListByContractId(contract.getId());
                    //calculate salary
                    Double salary = 0D;
                    salary = BASIC_SALARY * employeeCoefficientLevel.getValue();
                    System.out.println("employeeCoefficientLevel: " + employeeCoefficientLevel.getValue());
                    description += "Phụ cấp[ ";
                    for (BonusPercentValue bonusPercentValue : bonusPercentValueList) {
                        Double temp = salary * bonusPercentValue.getValue();
                        salary += temp;
                        description += bonusPercentValue.getBonusExactName() + ": " + temp + " đ. ";
                    }

                    for (BonusExactValue bonusExactValue : bonusExactValueList) {
                        salary += bonusExactValue.getValue();
                        description += bonusExactValue.getBonusExactName() + ": " + bonusExactValue.getValue() + ". ";
                    }
                    description += "]";

                    salary -= salary * INSURANCE_VALUE;
                    description += "Bảo hiểm[ -" + salary * INSURANCE_VALUE + "đ ]";
                    System.out.println(description);

                    //create new SalaryTable with Contract
                    SalaryTableContract salaryTableContract = new SalaryTableContract();
                    salaryTableContract.setContractId(contract.getId());
                    salaryTableContract.setSalaryTableId(salaryTable.getId());
                    salaryTableContract.setBasicSalary(BASIC_SALARY);
                    salaryTableContract.setWillPaymentSalary(salary);
                    salaryTableContract.setDescription(description);
                    salaryTableContract.setStatus(0);

                    //increase total value
                    Double finalSalary = salary;
                    totalValueOfSalaryTable.updateAndGet(v -> v + finalSalary);
                    return salaryTableContract;
                } else return null;
            }).filter(salaryTableContract -> salaryTableContract != null).collect(Collectors.toList());

            //end transaction
            salaryTable.setTotalValue(totalValueOfSalaryTable.get());
            salaryTableRepository.save(salaryTable);
            salaryTableContractRepository.saveAll(salaryTableContractList);
        }
        return null;
    }
}
