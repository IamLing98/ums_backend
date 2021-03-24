package com.linkdoan.backend.service;

import com.linkdoan.backend.dto.StudentGraduationDTO;

import java.util.List;

public interface StudentGraduationService {

    List<StudentGraduationDTO> getAll();

    List<StudentGraduationDTO> getAllByTerm(String termId);
}
