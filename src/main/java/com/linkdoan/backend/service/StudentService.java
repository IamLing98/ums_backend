package com.linkdoan.backend.service;

import com.linkdoan.backend.dto.StudentDTO;
import com.linkdoan.backend.model.Student;
 import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StudentService {
    Page getListStudent( Pageable  pageable);
    Student insertStudent(StudentDTO studentDTO);
}
