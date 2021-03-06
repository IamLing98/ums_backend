package com.linkdoan.backend.temp;

import com.linkdoan.backend.repository.SubjectClassRepository;
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

    @Autowired
    SubjectClassRepository scheduleService;

    @GetMapping(value = "/debug")
    public ResponseEntity<?> debug() {
        return new ResponseEntity<>("TestExcel2.test()", HttpStatus.OK);
    }
}
