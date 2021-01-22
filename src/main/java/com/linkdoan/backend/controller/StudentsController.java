package com.linkdoan.backend.controller;

import com.linkdoan.backend.dto.StudentDTO;
import com.linkdoan.backend.service.impl.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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

    @RequestMapping(value = "/students", method = RequestMethod.GET)
    public ResponseEntity<?> findBy(@RequestParam(name = "page", required = false) Integer page,
                                    @RequestParam(name = "pageSize", required = false) Integer pageSize,
                                    @RequestParam(name = "studentId", required = false) String studentId,
                                    @RequestParam(name = "startYear", required = false) Integer startYear,
                                    @RequestParam(name = "classId", required = false) String classId,
                                    @RequestParam(name = "departmentId", required = false) String departmentId)
            throws Exception {
        //        Example<Student> searchTerm = Example.of(new Student());
        return new ResponseEntity<>(studentService.findBy(page, pageSize, studentId, startYear, classId, departmentId), HttpStatus.OK);
    }

    @RequestMapping(value = "/students/{studentId}", method = RequestMethod.GET)
    public ResponseEntity<?> getDetails(@PathVariable("studentId" )String studentId) throws Exception {
        return new ResponseEntity<>(studentService.getDetails(studentId), HttpStatus.OK);
    }

    @RequestMapping(value = "/students/", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@Valid @RequestBody StudentDTO studentDTO) throws Exception {
        return new ResponseEntity<>(studentService.update(studentDTO), HttpStatus.OK);
    }


}
