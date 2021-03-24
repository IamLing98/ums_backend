package com.linkdoan.backend.controller;


import com.linkdoan.backend.dto.StudentGraduationDTO;
import com.linkdoan.backend.service.StudentGraduationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class StudentGraduationController {

    @Autowired
    StudentGraduationService studentGraduationService;

    @GetMapping("/studentGraduations")
    public ResponseEntity getAll(@RequestParam( value="termId") String termId) {
        return new ResponseEntity(studentGraduationService.getAllByTerm(termId), HttpStatus.OK);
    }

    @PostMapping("/studentGraduations")
    public ResponseEntity create(@RequestBody StudentGraduationDTO studentGraduationDTo) {
        return new ResponseEntity(studentGraduationService.getAll(), HttpStatus.OK);
    }

    @PutMapping("/studentGraduations")
    public ResponseEntity update(@RequestBody StudentGraduationDTO studentGraduationDTo) {
        return new ResponseEntity(studentGraduationService.getAll(), HttpStatus.OK);
    }

    @DeleteMapping("/studentGraduations/{graduationId}")
    public ResponseEntity delete(@PathVariable("graduationId") Long graduationId) {
        return new ResponseEntity(studentGraduationService.getAll(), HttpStatus.OK);
    }
}
