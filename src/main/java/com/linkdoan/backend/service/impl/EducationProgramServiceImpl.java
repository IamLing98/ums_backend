package com.linkdoan.backend.service.impl;

import com.linkdoan.backend.model.EducationProgram;
import com.linkdoan.backend.repository.EducationProgramRepository;
import com.linkdoan.backend.service.EducationProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EducationProgramServiceImpl implements EducationProgramService {

    @Autowired
    EducationProgramRepository educationProgramRepository;

    @Override
    public List<EducationProgram> getAllProgram() {
        return educationProgramRepository.findAll();
    }
}
