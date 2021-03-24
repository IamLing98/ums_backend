package com.linkdoan.backend.service.impl;

import com.linkdoan.backend.dto.StudentGraduationDTO;
import com.linkdoan.backend.model.StudentGraduation;
import com.linkdoan.backend.repository.StudentGraduationRepository;
import com.linkdoan.backend.service.StudentGraduationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentGraduationServiceImpl implements StudentGraduationService {

    @Autowired
    StudentGraduationRepository studentGraduationRepository;

    @Override
    public List<StudentGraduationDTO> getAll() {
//        List<GraduationDTO> graduationDTOList = studentGraduationRepository.findAll();
        return null;
    }

    @Override
    public List<StudentGraduationDTO> getAllByTerm(String termId) {
        List<StudentGraduationDTO> studentGraduationDTOS = studentGraduationRepository.findAllByTermId(termId);
        return studentGraduationDTOS;
    }

}
