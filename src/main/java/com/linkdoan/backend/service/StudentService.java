package com.linkdoan.backend.service;

import com.linkdoan.backend.dto.StudentDTO;
import com.linkdoan.backend.model.Student;
 import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;

public interface StudentService {
    Page getListStudent( Pageable  pageable);
    Student insertStudent(StudentDTO studentDTO);
    Student updateStudent(StudentDTO studentDTO) throws IOException;
    int deleteStudent(StudentDTO studentDTO);
    Page findBy(org.springframework.data.domain.Pageable pageable, StudentDTO studentDTO);
}
