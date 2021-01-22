package com.linkdoan.backend.service;

import com.linkdoan.backend.dto.EducationProgramDTO;

import java.util.List;

public interface EducationProgramService {

    List<EducationProgramDTO> getAllProgram(String branchId, String educationProgramId);

    EducationProgramDTO create(EducationProgramDTO educationProgramDTO);

    EducationProgramDTO update(String id, EducationProgramDTO educationProgramDTO);

    boolean delete(String id);

    EducationProgramDTO getDetail(String educationProgramId);
}
