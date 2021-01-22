package com.linkdoan.backend.service;

import com.linkdoan.backend.dto.StudentDTO;
import com.linkdoan.backend.dto.StudentDetailsDTO;
import org.springframework.context.annotation.ComponentScan;

import java.io.IOException;
import java.util.List;

@ComponentScan(basePackages = {"com.linkdoan.backend.*"})
public interface StudentService {

    StudentDTO update(StudentDTO studentDTO) throws IOException;

    List<StudentDTO> findBy(Integer page, Integer pageSize, String studentId, Integer startYear, String classId, String departmentId) throws IOException;

    StudentDetailsDTO getDetails(String studentId);
}
