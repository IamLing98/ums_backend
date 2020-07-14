package com.linkdoan.backend.service;

import com.linkdoan.backend.dto.StudentDTO;
import com.linkdoan.backend.model.Student;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.io.IOException;

@ComponentScan(basePackages= {"com.linkdoan.backend.*"})
public interface StudentService {
    Page getListStudent( Pageable  pageable) throws IOException;
    Student insertStudent(StudentDTO studentDTO) throws IOException;
    Student updateStudent(StudentDTO studentDTO) throws IOException;
    int deleteStudent(StudentDTO studentDTO) throws IOException;
    Page findBy(org.springframework.data.domain.Pageable pageable, StudentDTO studentDTO)throws IOException;
}
