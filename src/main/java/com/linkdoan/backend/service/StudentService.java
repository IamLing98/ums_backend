package com.linkdoan.backend.service;

import com.linkdoan.backend.dto.StudentDTO;
import org.springframework.context.annotation.ComponentScan;

import java.io.IOException;
import java.util.List;

@ComponentScan(basePackages = {"com.linkdoan.backend.*"})
public interface StudentService {

    List<StudentDTO> findBy(Integer page, Integer pageSize, String studentId, Integer startYear, String classId, String departmentId) throws IOException;

    StudentDTO getDetail(String studentId);

    List<StudentDTO>  update(List<StudentDTO> studentDTOS) throws IOException;

    int create(List<StudentDTO> studentDTOS);

    int delete(List<String> ids);
}
