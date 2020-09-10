package com.linkdoan.backend.controller;

import com.linkdoan.backend.dto.StudentDTO;
import com.linkdoan.backend.service.impl.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping(value = "/student/getAllStudent", method = RequestMethod.POST)
    public ResponseEntity<?> findBy(@Valid @ModelAttribute StudentDTO studentDTO) throws Exception {
        //        Example<Student> searchTerm = Example.of(new Student());
        return new ResponseEntity<>(studentService.findBy(studentDTO), HttpStatus.OK);
    }

}
