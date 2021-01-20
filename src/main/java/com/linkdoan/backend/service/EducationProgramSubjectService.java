package com.linkdoan.backend.service;

import com.linkdoan.backend.dto.EducationProgramSubjectDTO;

import java.util.List;
import java.util.Map;

public interface EducationProgramSubjectService {

    int create(List<EducationProgramSubjectDTO> educationProgramSubjectDTOList);

    List<Map<String, Object>> getListSubjectCorrectWithEP(String id, boolean isIn);

    EducationProgramSubjectDTO change(String eduId, String sjId, EducationProgramSubjectDTO educationProgramSubjectDTO);

    int delete(String educationProgramId, List<Long> ids);
}
