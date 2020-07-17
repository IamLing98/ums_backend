package com.linkdoan.backend.controller;

import com.linkdoan.backend.dto.ClassDTO;
import com.linkdoan.backend.dto.StudentDTO;
import com.linkdoan.backend.model.Class;
import com.linkdoan.backend.repository.ClassRepository;
import com.linkdoan.backend.service.ClassService;
import com.linkdoan.backend.service.impl.ClassServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class ClassController {

    @Autowired
    private ClassServiceImpl classService;

    @PostMapping("/class/getAll")
    public ResponseEntity<?> findBy(@Valid @ModelAttribute ClassDTO classDTO) throws Exception {
        return new ResponseEntity<>(this.classService.findBy(classDTO), HttpStatus.OK);
    }

    @RequestMapping(value="/class/create",method = RequestMethod.POST)
    public ResponseEntity<?> create(@Valid @ModelAttribute ClassDTO classDTO) throws Exception {
        return new ResponseEntity<>(classService.createClass(classDTO), HttpStatus.OK);
    }

    @RequestMapping(value="/class/update",method = RequestMethod.POST)
    public ResponseEntity<?> update(@Valid @ModelAttribute ClassDTO classDTO) throws Exception {
        return new ResponseEntity<>(classService.updateClass(classDTO), HttpStatus.OK);
    }

    @RequestMapping(value="/class/delete",method = RequestMethod.POST)
    public ResponseEntity<?> delete(@Valid @ModelAttribute ClassDTO classDTO) throws Exception {
        return new ResponseEntity<>(classService.deleteClass(classDTO), HttpStatus.OK);
    }
}
