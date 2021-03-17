package com.linkdoan.backend.controller;

import com.linkdoan.backend.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResultController {

    @Autowired
    ResultService resultService;

    @GetMapping(value = "/results")
    public ResponseEntity<?> getResult(@RequestParam(name = "termId") String termId
    ) {
        return new ResponseEntity(resultService.getResult(termId), HttpStatus.OK);
    }

    @GetMapping(value = "/results/details/{studentId}")
    public ResponseEntity<?> getStudentResultDetail(@PathVariable("studentId") String studentId) {
        return new ResponseEntity(resultService.getDetail(studentId), HttpStatus.OK);
    }
}
