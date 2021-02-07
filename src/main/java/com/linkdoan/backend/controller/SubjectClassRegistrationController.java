package com.linkdoan.backend.controller;

import com.linkdoan.backend.dto.SubjectClassRegistrationDTO;
import com.linkdoan.backend.service.SubjectClassRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
public class SubjectClassRegistrationController {

    @Autowired
    SubjectClassRegistrationService subjectClassRegistrationService;

    @RequestMapping(value = "/subjectClassRegistration/{termId}", method = RequestMethod.GET)
    public ResponseEntity<?> getListSubmitted(@PathVariable("termId") String termId, SecurityContextHolder request)
            throws Exception {
        String studentId = request.getContext().getAuthentication().getName();
        System.out.println(studentId);
        return new ResponseEntity<>(subjectClassRegistrationService.getListSubmitted(studentId, termId), HttpStatus.OK);
    }

    @RequestMapping(value = "/subjectClassRegistration", method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody SubjectClassRegistrationDTO subjectClassRegistrationDTO, SecurityContextHolder request)
            throws Exception {
        String studentId = request.getContext().getAuthentication().getName();
        System.out.println(studentId);
        return new ResponseEntity<>(subjectClassRegistrationService.submit(studentId, subjectClassRegistrationDTO), HttpStatus.OK);
    }

    @RequestMapping(value = "/subjectClassRegistration/{scheduleId}/{subjectClassId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable("scheduleId") Long scheduleId, @PathVariable("subjectClassId") String subjectClassId, SecurityContextHolder request)
            throws Exception {
        String studentId = request.getContext().getAuthentication().getName();
        System.out.println(studentId);
        return new ResponseEntity<>(subjectClassRegistrationService.delete(studentId, subjectClassId, scheduleId), HttpStatus.OK);
    }
}
