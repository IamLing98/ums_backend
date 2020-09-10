package com.linkdoan.backend.service;

import com.linkdoan.backend.dto.StudentDTO;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;

import java.io.IOException;

@ComponentScan(basePackages= {"com.linkdoan.backend.*"})
public interface StudentService {
//    Student insertStudent(StudentDTO studentDTO) throws IOException, ParseException;
//    Student updateStudent(StudentDTO studentDTO) throws IOException, ParseException;
//    int deleteStudent(StudentDTO studentDTO) throws IOException, ParseException;
    Page findBy(StudentDTO studentDTO)throws IOException;
}
