package com.linkdoan.backend.controller;


import com.linkdoan.backend.dto.GraduationDTO;
import com.linkdoan.backend.service.GraduationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class SchoolYearController {

    @Autowired
    GraduationService graduationService;

    @GetMapping("/schoolYear")
    public ResponseEntity getAll() {
        return new ResponseEntity(graduationService.getAll(), HttpStatus.OK);
    }

    @PostMapping("/schoolYear")
    public ResponseEntity create(@RequestBody GraduationDTO graduationDTO) {
        return new ResponseEntity(graduationService.getAll(), HttpStatus.OK);
    }

    @PutMapping("/schoolYear")
    public ResponseEntity update(@RequestBody GraduationDTO graduationDTO) {
        return new ResponseEntity(graduationService.getAll(), HttpStatus.OK);
    }

    @DeleteMapping("/schoolYear/{schoolYearId}")
    public ResponseEntity delete(@PathVariable("schoolYearId") String yearSchoolId) {
        return new ResponseEntity(graduationService.getAll(), HttpStatus.OK);
    }
}
