package com.linkdoan.backend.controller;


import com.linkdoan.backend.dto.StudentGraduationDTO;
import com.linkdoan.backend.service.SchoolYearService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class SchoolYearController {

    @Autowired
    SchoolYearService schoolYearService;

    @GetMapping("/schoolYears")
    public ResponseEntity getAll() {
        return new ResponseEntity(schoolYearService.getAll(), HttpStatus.OK);
    }

    @PostMapping("/schoolYears")
    public ResponseEntity create(@RequestBody StudentGraduationDTO studentGraduationDTo) {
        return new ResponseEntity(schoolYearService.getAll(), HttpStatus.OK);
    }

    @PutMapping("/schoolYears")
    public ResponseEntity update(@RequestBody StudentGraduationDTO studentGraduationDTo) {
        return new ResponseEntity(schoolYearService.getAll(), HttpStatus.OK);
    }

    @DeleteMapping("/schoolYears/{schoolYearId}")
    public ResponseEntity delete(@PathVariable("schoolYearId") String yearSchoolId) {
        return new ResponseEntity(schoolYearService.getAll(), HttpStatus.OK);
    }
}
