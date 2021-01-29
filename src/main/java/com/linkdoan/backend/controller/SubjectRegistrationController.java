package com.linkdoan.backend.controller;

import com.linkdoan.backend.dto.SubjectRegistrationDTO;
import com.linkdoan.backend.service.SubjectRegistrationService;
import com.linkdoan.backend.service.impl.StudentServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
public class SubjectRegistrationController {

    private static Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

    @Autowired
    SubjectRegistrationService subjectRegistrationService;

    //    admin section
    @RequestMapping(value = "/subjectsRegistration/{termId}", method = RequestMethod.GET)
    public ResponseEntity<?> findBy(@PathVariable("termId") String termId, SecurityContextHolder request)
            throws Exception {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("PDT"))) {
            return new ResponseEntity<>(subjectRegistrationService.getSubmittingInfo(termId), HttpStatus.OK);
        }
        logger.info(request.getContext().getAuthentication().getName());
        return new ResponseEntity<>(subjectRegistrationService.getListSubjectSubmitted(termId, request.getContext().getAuthentication().getName()), HttpStatus.OK);
    }

    @RequestMapping(value = "/subjectsRegistration", method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody SubjectRegistrationDTO subjectRegistrationDTO, SecurityContextHolder request)
            throws Exception {
        String studentId = request.getContext().getAuthentication().getName();
        System.out.println(studentId);
        return new ResponseEntity<>(subjectRegistrationService.addSubject(studentId, subjectRegistrationDTO), HttpStatus.OK);
    }

    @RequestMapping(value = "/subjectsRegistration/{subjectId}/{termId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable("subjectId") String subjectId, @PathVariable("termId") String termId, SecurityContextHolder request)
            throws Exception {
        String studentId = request.getContext().getAuthentication().getName();
        return new ResponseEntity<>(subjectRegistrationService.deleteSubject(studentId, subjectId, termId), HttpStatus.OK);
    }

    //    student section
    //get list submitted

}
