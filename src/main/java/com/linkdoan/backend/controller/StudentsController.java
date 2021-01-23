package com.linkdoan.backend.controller;

import com.linkdoan.backend.dto.StudentDTO;
import com.linkdoan.backend.model.Student;
import com.linkdoan.backend.service.StudentService;
import com.linkdoan.backend.service.impl.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController


public class StudentsController {

    private StudentService studentService;

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
        return new ResponseEntity<>(studentService.getDetail(studentId), HttpStatus.OK);
    }

    @RequestMapping(value = "/students", method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody List<StudentDTO> studentDTOList) throws Exception {
        return new ResponseEntity<>(studentService.create(studentDTOList), HttpStatus.CREATED);
    }

    @PutMapping(value = "/students" )
    public ResponseEntity<?> update( @RequestBody List<StudentDTO> studentDTOList) throws Exception {
        return new ResponseEntity<>(studentService.update(studentDTOList), HttpStatus.OK);
    }

    @DeleteMapping(value = "/students" )
    public ResponseEntity<?> delete( @RequestParam("ids") List<String> ids) throws Exception {
        return new ResponseEntity<>(studentService.delete(ids), HttpStatus.OK);
    }


}
