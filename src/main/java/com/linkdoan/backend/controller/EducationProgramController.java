package com.linkdoan.backend.controller;

import com.linkdoan.backend.model.EducationProgram;
import com.linkdoan.backend.service.impl.EducationProgramServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EducationProgramController {

    @Autowired
    EducationProgramServiceImpl educationProgramServiceImpl ;

    @RequestMapping(value = "/education-program/getAllProgram", method = RequestMethod.GET)
    public ResponseEntity<?> getAll() throws Exception {
        List<EducationProgram> rs = educationProgramServiceImpl.getAllProgram();
        return new ResponseEntity<>(rs, HttpStatus.OK);
    }

}
