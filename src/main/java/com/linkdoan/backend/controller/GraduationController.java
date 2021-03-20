package com.linkdoan.backend.controller;


import com.linkdoan.backend.dto.GraduationDTO;
import com.linkdoan.backend.service.GraduationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class GraduationController {

    @Autowired
    GraduationService graduationService;

    @GetMapping("/graduations")
    public ResponseEntity getAll() {
        return new ResponseEntity(graduationService.getAll(), HttpStatus.OK);
    }

    @PostMapping("/graduations")
    public ResponseEntity create(@RequestBody GraduationDTO graduationDTO) {
        return new ResponseEntity(graduationService.getAll(), HttpStatus.OK);
    }

    @PutMapping("/graduations")
    public ResponseEntity update(@RequestBody GraduationDTO graduationDTO) {
        return new ResponseEntity(graduationService.getAll(), HttpStatus.OK);
    }

    @DeleteMapping("/graduations/{graduationId}")
    public ResponseEntity delete(@PathVariable("graduationId") Long graduationId) {
        return new ResponseEntity(graduationService.getAll(), HttpStatus.OK);
    }
}
