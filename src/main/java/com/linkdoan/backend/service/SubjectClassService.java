package com.linkdoan.backend.service;

import com.linkdoan.backend.dto.SubjectClassDTO;
import com.linkdoan.backend.model.SubjectClass;

import java.util.List;
import java.util.Map;

public interface SubjectClassService {

    List<Map<String, Object>> getAll(String termId);

    Map<String, Object> getDetail(String subjectClassId);

    List<SubjectClass> create(List<SubjectClassDTO> subjectDTO);

    int update(List<SubjectClassDTO> subjectClassDTOList, String actionType);

    int delete(List<String > ids);

}
