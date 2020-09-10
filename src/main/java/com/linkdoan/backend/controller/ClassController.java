package com.linkdoan.backend.controller;

import com.linkdoan.backend.dto.YearClassDTO;
import com.linkdoan.backend.service.impl.ClassServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ClassController {

    @Autowired
    private ClassServiceImpl classService;

    @PostMapping("/class/getAllYearClass")
    public ResponseEntity<?> findBy(@RequestBody YearClassDTO yearClassDTO) throws Exception {
        return new ResponseEntity<>(this.classService.findBy(yearClassDTO), HttpStatus.OK);
    }

    @RequestMapping(value = "/yearClass/create", method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody YearClassDTO yearClassDTO) throws Exception {
        return new ResponseEntity<>(classService.createClass(yearClassDTO), HttpStatus.OK);
    }

    @RequestMapping(value = "/yearClass/update", method = RequestMethod.POST)
    public ResponseEntity<?> update(@RequestBody YearClassDTO yearClassDTO) throws Exception {
        return new ResponseEntity<>(classService.updateClass(yearClassDTO), HttpStatus.OK);
    }

    @RequestMapping(value = "/yearClass/delete", method = RequestMethod.POST)
    public ResponseEntity<?> delete(@RequestBody YearClassDTO yearClassDTO) throws Exception {
        return new ResponseEntity<>(classService.deleteClass(yearClassDTO), HttpStatus.OK);
    }
}
