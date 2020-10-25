package com.linkdoan.backend.controller;

import com.linkdoan.backend.dto.SubjectRegistrationDTO;
import com.linkdoan.backend.dto.TermDTO;
import com.linkdoan.backend.model.SubjectRegistration;
import com.linkdoan.backend.service.impl.StudentServiceImpl;
import com.linkdoan.backend.service.impl.SubjectRegistrationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class SubjectRegistrationController {

    private static Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

    @Autowired
    SubjectRegistrationServiceImpl subjectRegistrationService;

    @RequestMapping(value = "/subjectsRegistration", method = RequestMethod.GET)
    public ResponseEntity<?> findBy(SecurityContextHolder request)
            throws Exception {
        //        Example<Student> searchTerm = Example.of(new Student());
        logger.info(request.getContext().getAuthentication().getName());
        return new ResponseEntity<>(subjectRegistrationService.getAll(request.getContext().getAuthentication().getName()), HttpStatus.OK);
    }

    @RequestMapping(value = "/subjectsRegistration", method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody SubjectRegistrationDTO subjectRegistrationDTO, SecurityContextHolder request)
            throws Exception {
        //        Example<Student> searchTerm = Example.of(new Student());
        String studentId = request.getContext().getAuthentication().getName();
        return new ResponseEntity<>(subjectRegistrationService.addSubject(studentId,subjectRegistrationDTO), HttpStatus.OK);
    }

    @RequestMapping(value = "/subjectsRegistration/{subjectId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable String subjectId,SecurityContextHolder request)
            throws Exception {
        //        Example<Student> searchTerm = Example.of(new Student());
        String studentId = request.getContext().getAuthentication().getName();
        return new ResponseEntity<>(subjectRegistrationService.deleteSubject(studentId,subjectId), HttpStatus.OK);
    }

}
