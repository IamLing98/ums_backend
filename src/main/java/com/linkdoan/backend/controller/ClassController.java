package com.linkdoan.backend.controller;

import com.linkdoan.backend.dto.ClassDTO;
import com.linkdoan.backend.dto.StudentDTO;
import com.linkdoan.backend.model.Class;
import com.linkdoan.backend.repository.ClassRepository;
import com.linkdoan.backend.service.ClassService;
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
public class ClassController {

    @Autowired
    private ClassService classService;

    @RequestMapping(value="/class/findBy")
    public ResponseEntity<?> findBy(@Valid @ModelAttribute ClassDTO classDTO) throws Exception {
        Pageable pageable = PageRequest.of(classDTO.getPage(), classDTO.getPageSize());
        return new ResponseEntity<>(classService.findBy(pageable,classDTO), HttpStatus.OK);
    }
    @RequestMapping(value="/class/create")
    public ResponseEntity<?> create(@Valid @ModelAttribute ClassDTO classDTO) throws Exception {
        return new ResponseEntity<>(classService.createClass(classDTO), HttpStatus.OK);
    }
    @RequestMapping(value="/class/update")
    public ResponseEntity<?> update(@Valid @ModelAttribute ClassDTO classDTO) throws Exception {
        return new ResponseEntity<>(classService.updateClass(classDTO), HttpStatus.OK);
    }
    @RequestMapping(value="/class/delete")
    public ResponseEntity<?> delete(@Valid @ModelAttribute ClassDTO classDTO) throws Exception {
        return new ResponseEntity<>(classService.deleteClass(classDTO), HttpStatus.OK);
    }
}
