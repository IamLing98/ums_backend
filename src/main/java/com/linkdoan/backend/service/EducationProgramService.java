package com.linkdoan.backend.service;

import com.linkdoan.backend.dto.EducationProgramDTO;
import com.linkdoan.backend.dto.EducationProgramSubjectDTO;
import com.linkdoan.backend.model.EducationProgramSubject;

import java.util.List;

public interface EducationProgramService {
    List<EducationProgramDTO> getAllProgram();
    EducationProgramDTO createNewEducationProgram(EducationProgramDTO educationProgramDTO);
    EducationProgramDTO updateEducationProgram(EducationProgramDTO educationProgramDTO);
    boolean delete(EducationProgramDTO educationProgramDTO);
    List<EducationProgramSubject> updateEducationProgramSubject(List<EducationProgramSubjectDTO> educationProgramSubjectDTOList);
    EducationProgramDTO getDetails(EducationProgramDTO educationProgramDTO);
}
