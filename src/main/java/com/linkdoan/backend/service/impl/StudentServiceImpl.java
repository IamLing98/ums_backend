package com.linkdoan.backend.service.impl;

import com.linkdoan.backend.dto.StudentDTO;
import com.linkdoan.backend.model.Student;
import com.linkdoan.backend.repository.StudentRepository;
import com.linkdoan.backend.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentRepository studentRepository;



    @Override
    public Page<Student> getListStudent( Pageable pageable) {
          return studentRepository.findAll( pageable);
    }

    @Override
    public Student insertStudent(StudentDTO studentDTO) {
        return studentRepository.save(studentDTO.toModel());
    }
}
