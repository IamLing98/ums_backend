package com.linkdoan.backend.service;

import com.linkdoan.backend.dto.EducationProgramDTO;
import com.linkdoan.backend.model.EducationProgramSubject;

import java.util.List;

public interface EducationProgramService {
    List<EducationProgramDTO> getAllProgram();
    EducationProgramDTO createNewEducationProgram(EducationProgramDTO educationProgramDTO);
    EducationProgramDTO updateEducationProgram(EducationProgramDTO educationProgramDTO);
    boolean delete(List<EducationProgramDTO> educationProgramDTOList);
    List<EducationProgramSubject> updateEducationProgramSubject(EducationProgramDTO educationProgramDTO);
    EducationProgramDTO getDetails(EducationProgramDTO educationProgramDTO);
}
