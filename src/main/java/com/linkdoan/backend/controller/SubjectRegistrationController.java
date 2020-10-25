package com.linkdoan.backend.controller;

import com.linkdoan.backend.dto.TermDTO;
import com.linkdoan.backend.service.impl.SubjectRegistrationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class SubjectRegistrationController {

    @Autowired
    SubjectRegistrationServiceImpl subjectRegistrationService;

    @RequestMapping(value = "/subjectsRegistration", method = RequestMethod.GET)
    public ResponseEntity<?> findBy(@RequestParam(name = "year", required = false) Integer year,
                                    @RequestParam(name = "term", required = false) Integer term)
            throws Exception {
        //        Example<Student> searchTerm = Example.of(new Student());
        return new ResponseEntity<>(subjectRegistrationService.getAll(year, term), HttpStatus.OK);
    }

    @RequestMapping(value = "/subjectsRegistration", method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody TermDTO termDTO)
            throws Exception {
        //        Example<Student> searchTerm = Example.of(new Student());
        return new ResponseEntity<>(subjectRegistrationService.create(termDTO), HttpStatus.OK);
    }

    @RequestMapping(value = "/subjectsRegistration", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@RequestBody TermDTO termDTO)
            throws Exception {
        //        Example<Student> searchTerm = Example.of(new Student());
        return new ResponseEntity<>(subjectRegistrationService.update(termDTO), HttpStatus.OK);
    }

    @RequestMapping(value = "/subjectsRegistration", method = RequestMethod.DELETE)
    public ResponseEntity<?> update(@RequestParam(name = "id", required = true) Long id)
            throws Exception {
        //        Example<Student> searchTerm = Example.of(new Student());
        return new ResponseEntity<>(subjectRegistrationService.delete(id), HttpStatus.OK);
    }
}
