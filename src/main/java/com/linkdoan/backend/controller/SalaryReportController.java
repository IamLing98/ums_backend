package com.linkdoan.backend.controller;

import com.linkdoan.backend.dto.SalaryTableContractDTO;
import com.linkdoan.backend.service.SalaryReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class SalaryReportController {

    @Autowired
    SalaryReportService salaryReportService;

    @GetMapping("/salaryTableContracts")
    public ResponseEntity getAll() {
        return new ResponseEntity(salaryReportService.getAll(), HttpStatus.OK);
    }

    @PostMapping("/salaryTableContracts")
    public ResponseEntity create(@RequestBody SalaryTableContractDTO salaryTableContractDTO) {
        return new ResponseEntity(salaryReportService.create(salaryTableContractDTO), HttpStatus.OK);
    }

    @PutMapping("/salaryTableContracts/{id}")
    public ResponseEntity update(@RequestBody SalaryTableContractDTO salaryTableContractDTO) {
        return new ResponseEntity(salaryReportService.create(salaryTableContractDTO), HttpStatus.OK);
    }

    @DeleteMapping("/salaryTableContracts/{id}")
    public ResponseEntity delete(@RequestBody SalaryTableContractDTO salaryTableContractDTO) {
        return new ResponseEntity(salaryReportService.create(salaryTableContractDTO), HttpStatus.OK);
    }

}
