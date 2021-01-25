package com.linkdoan.backend.controller;

import com.linkdoan.backend.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TeacherController {

    @Autowired
    TeacherService teacherService;

    @RequestMapping(value = "/teachers", method = RequestMethod.GET)
    public ResponseEntity<?> getAll()
            throws Exception {
        //        Example<Student> searchTerm = Example.of(new Student());
        return new ResponseEntity<>(teacherService.getAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/teachers/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getDetail(@PathVariable String id)
            throws Exception {
        //        Example<Student> searchTerm = Example.of(new Student());
        return new ResponseEntity<>(teacherService.getAll(), HttpStatus.OK);
    }
}
