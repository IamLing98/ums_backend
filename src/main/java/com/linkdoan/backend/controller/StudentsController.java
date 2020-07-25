package com.linkdoan.backend.controller;

import com.linkdoan.backend.dto.StudentDTO;
import com.linkdoan.backend.model.JwtRequest;
import com.linkdoan.backend.model.Student;
import com.linkdoan.backend.service.impl.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController


public class StudentsController {

    @Resource(name = "studentService")
    private StudentServiceImpl studentService;

    public StudentServiceImpl getStudentService(StudentServiceImpl studentService) {
        return studentService;
    }

    @Autowired
    public void setStudentService(StudentServiceImpl studentService) {
        this.studentService = studentService;
    }

    @RequestMapping(value = "/student/getAll", method = RequestMethod.POST)
    public ResponseEntity<?> findBy(@Valid @ModelAttribute StudentDTO studentDTO) throws Exception {
        //        Example<Student> searchTerm = Example.of(new Student());
        return new ResponseEntity<>(studentService.findBy(studentDTO), HttpStatus.OK);
    }

    @RequestMapping(value = "/student/create", method = RequestMethod.POST)
    public ResponseEntity<?> insertStudent(@Valid @ModelAttribute StudentDTO studentDTO) throws Exception {

        return new ResponseEntity<>(studentService.insertStudent(studentDTO), HttpStatus.OK);
    }

    @RequestMapping(value = "/student/update", method = RequestMethod.POST)
    public ResponseEntity<?> updateStudent(@Valid @ModelAttribute StudentDTO studentDTO) throws Exception {

        return new ResponseEntity<>(studentService.updateStudent(studentDTO), HttpStatus.OK);
    }

    @RequestMapping(value = "/student/delete", method = RequestMethod.POST)
    public ResponseEntity<?> deleteStudent(@Valid @ModelAttribute StudentDTO studentDTO) throws Exception {
        if (studentService.deleteStudent(studentDTO) == 0)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(HttpStatus.OK);
    }
}
