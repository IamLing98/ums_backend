package com.linkdoan.backend.service;

import com.linkdoan.backend.dto.SubjectClassDTO;
import com.linkdoan.backend.dto.SubjectDTO;
import com.linkdoan.backend.model.SubjectClass;

import java.util.List;

public interface SubjectClassService {

    List<SubjectClassDTO> getAll(String termId);

    SubjectClassDTO getDetail(String subjectClassId);

    SubjectDTO create(List<SubjectClassDTO> subjectDTO);

    SubjectDTO update(String subjectClassId, SubjectClassDTO subjectDTO);

    boolean delete(String subjectClassDTOList);

}
