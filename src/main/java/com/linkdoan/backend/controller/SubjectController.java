package com.linkdoan.backend.controller;

import com.linkdoan.backend.dto.StudentDTO;
import com.linkdoan.backend.dto.SubjectDTO;
import com.linkdoan.backend.model.Student;
import com.linkdoan.backend.service.impl.SubjectServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class SubjectController {
    @Autowired
    SubjectServiceImpl subjectService;


    @RequestMapping(value = "/subject/findBy", method = RequestMethod.POST)
    public ResponseEntity<?> findBy(@Valid @ModelAttribute SubjectDTO subjectDTO) throws Exception {
        Pageable pageable = PageRequest.of(subjectDTO.getPage(), subjectDTO.getPageSize());
        return new ResponseEntity<>(subjectService.findBy(pageable, subjectDTO), HttpStatus.OK);
    }

    @RequestMapping(value = "/subject/insert", method = RequestMethod.POST)
    public ResponseEntity<?> insertStudent(@Valid @ModelAttribute SubjectDTO subjectDTO) throws Exception {

        return new ResponseEntity<>(subjectService.createSubject(subjectDTO), HttpStatus.OK);
    }

    @RequestMapping(value = "/subject/update", method = RequestMethod.POST)
    public ResponseEntity<?> updateStudent(@Valid @ModelAttribute SubjectDTO subjectDTO) throws Exception {

        return new ResponseEntity<>(subjectService.updateSubject(subjectDTO), HttpStatus.OK);
    }

    @RequestMapping(value = "/subject/delete", method = RequestMethod.POST)
    public ResponseEntity<?> deleteStudent(@Valid @ModelAttribute SubjectDTO subjectDTO) throws Exception {
        if (subjectService.deleteSubject(subjectDTO) == 0)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(HttpStatus.OK);
    }
}
