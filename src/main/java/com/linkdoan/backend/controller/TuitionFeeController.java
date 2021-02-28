package com.linkdoan.backend.controller;

import com.linkdoan.backend.service.TuitionFeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TuitionFeeController {

    @Autowired
    TuitionFeeService tuitionFeeService;

    @GetMapping(value = "/tuitionFee/{termId}/{studentId}")
    public ResponseEntity<?> getFeeInfoOfStudentInTerm(@PathVariable("termId") String termId, @PathVariable("studentId") String studentId) {
        return new ResponseEntity<>(tuitionFeeService.getFeeInfoOfStudent(studentId, termId), HttpStatus.OK);
    }
}
