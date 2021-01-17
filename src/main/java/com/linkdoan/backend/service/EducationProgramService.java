package com.linkdoan.backend.service;

import com.linkdoan.backend.dto.EducationProgramDTO;
import com.linkdoan.backend.model.EducationProgramSubject;

import java.util.List;

public interface EducationProgramService {
    List<EducationProgramDTO> getAllProgram(String branchId, String educationProgramId);
    EducationProgramDTO create(EducationProgramDTO educationProgramDTO);
    EducationProgramDTO update(String id, EducationProgramDTO educationProgramDTO);
    boolean delete(String id);
    List<EducationProgramSubject> updateEducationProgramSubject(EducationProgramDTO educationProgramDTO);
    EducationProgramDTO getDetails(String educationProgramDTO);
}
