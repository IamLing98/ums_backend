package com.linkdoan.backend.service;

import com.linkdoan.backend.dto.EducationProgramDTO;

import java.io.FileNotFoundException;
import java.util.List;

public interface EducationProgramService {

    EducationProgramDTO getDetailWithResult(String educationProgramId);

    List<EducationProgramDTO> getAllProgram(String branchId, String educationProgramId);

    EducationProgramDTO create(EducationProgramDTO educationProgramDTO) throws FileNotFoundException;

    EducationProgramDTO update(String id, EducationProgramDTO educationProgramDTO);

    boolean delete(String id);

    EducationProgramDTO getDetail(String educationProgramId);
}
