package com.linkdoan.backend.controller;

import com.linkdoan.backend.dto.SubjectDTO;
import com.linkdoan.backend.service.impl.SubjectServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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

    @RequestMapping(value = "/subject/getAll", method = RequestMethod.GET)
    public ResponseEntity<?> getAll() throws Exception {
        return new ResponseEntity<>(subjectService.getAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/subject/create", method = RequestMethod.POST)
    public ResponseEntity<?> create(@Valid @ModelAttribute SubjectDTO subjectDTO) throws Exception {
        return new ResponseEntity<>(subjectService.create(subjectDTO), HttpStatus.OK);
    }

    @RequestMapping(value = "/subject/update", method = RequestMethod.POST)
    public ResponseEntity<?> update(@Valid @ModelAttribute SubjectDTO subjectDTO) throws Exception {
        return new ResponseEntity<>(subjectService.update(subjectDTO), HttpStatus.OK);
    }

    @RequestMapping(value = "/subject/delete", method = RequestMethod.POST)
    public ResponseEntity<?> delete(@Valid @ModelAttribute SubjectDTO subjectDTO) throws Exception {
        if (subjectService.delete(subjectDTO) == false)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(HttpStatus.OK);
    }
}
