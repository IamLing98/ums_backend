package com.linkdoan.backend.controller;

import com.linkdoan.backend.dto.ContractDTO;
import com.linkdoan.backend.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ContractController {

    @Autowired
    ContractService contractService;

    @GetMapping("/contracts")
    public ResponseEntity getAll() {
        return new ResponseEntity(contractService.getAll(), HttpStatus.OK);
    }

    @PostMapping("/contracts")
    public ResponseEntity createContract(@RequestBody ContractDTO contractDTO) {
        return new ResponseEntity(contractService.create(contractDTO), HttpStatus.OK);
    }

    @PutMapping("/contracts")
    public ResponseEntity updateContract(@RequestBody ContractDTO contractDTO) {
        return new ResponseEntity(contractService.create(contractDTO), HttpStatus.OK);
    }
}
