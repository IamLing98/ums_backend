package com.linkdoan.backend.controller;

import com.linkdoan.backend.service.TuitionFeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DebugController {

    @Autowired
    TuitionFeeService tuitionFeeService;

    @GetMapping(value = "/debug")
    public ResponseEntity<?> debug(){
        return new ResponseEntity<>(tuitionFeeService.debug(), HttpStatus.OK);
    }
}
