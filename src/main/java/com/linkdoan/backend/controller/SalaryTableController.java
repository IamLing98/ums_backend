package com.linkdoan.backend.controller;

import com.linkdoan.backend.dto.SalaryTableDTO;
import com.linkdoan.backend.service.SalaryTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class SalaryTableController {

    @Autowired
    SalaryTableService salaryTableService;

    @GetMapping("/salaryTables")
    public ResponseEntity getAll() {
        return new ResponseEntity(salaryTableService.getAll(), HttpStatus.OK);
    }

    @PostMapping("/salaryTables")
    public ResponseEntity create(@RequestBody SalaryTableDTO salaryTableDTO) {
        return new ResponseEntity(salaryTableService.create(salaryTableDTO), HttpStatus.OK);
    }

    @PutMapping("/salaryTables/{id}")
    public ResponseEntity update(@RequestBody SalaryTableDTO salaryTableDTO) {
        return new ResponseEntity(salaryTableService.create(salaryTableDTO), HttpStatus.OK);
    }

    @DeleteMapping("/salaryTables/{id}")
    public ResponseEntity delete(@RequestBody SalaryTableDTO salaryTableDTO) {
        return new ResponseEntity(salaryTableService.create(salaryTableDTO), HttpStatus.OK);
    }
}
