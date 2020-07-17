package com.linkdoan.backend.service;

import com.linkdoan.backend.dto.StudentDTO;
import com.linkdoan.backend.model.Student;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import java.io.IOException;

@ComponentScan(basePackages= {"com.linkdoan.backend.*"})
public interface StudentService {
    Student insertStudent(StudentDTO studentDTO) throws IOException;
    Student updateStudent(StudentDTO studentDTO) throws IOException;
    int deleteStudent(StudentDTO studentDTO) throws IOException;
    Page findBy(StudentDTO studentDTO)throws IOException;
}
