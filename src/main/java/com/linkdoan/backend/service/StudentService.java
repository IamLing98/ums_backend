package com.linkdoan.backend.service;

import com.linkdoan.backend.dto.StudentDTO;
import com.linkdoan.backend.dto.StudentDetailsDTO;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;

import java.io.IOException;

@ComponentScan(basePackages = {"com.linkdoan.backend.*"})
public interface StudentService {
    //    Student insertStudent(StudentDTO studentDTO) throws IOException, ParseException;
    StudentDTO update(StudentDTO studentDTO) throws IOException;
    //    int deleteStudent(StudentDTO studentDTO) throws IOException, ParseException;
    Page findBy(Integer page, Integer pageSize, String studentId, Integer startYear, String classId, String departmentId) throws IOException;

    StudentDetailsDTO getDetails(String studentId);
}
