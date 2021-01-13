package com.linkdoan.backend.service;

import com.linkdoan.backend.dto.SubjectClassDTO;
import com.linkdoan.backend.model.SubjectClass;

import java.util.List;
import java.util.Map;

public interface SubjectClassService {

    List<Map<String, Object>> getAll(String termId);

    SubjectClassDTO getDetail(String subjectClassId);

    List<SubjectClass> create(List<SubjectClassDTO> subjectDTO);

    int update(String subjectClassId, SubjectClassDTO subjectDTO);

    boolean delete(String subjectClassDTOList);

}
