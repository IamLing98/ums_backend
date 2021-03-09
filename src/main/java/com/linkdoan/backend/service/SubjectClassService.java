package com.linkdoan.backend.service;

import com.linkdoan.backend.dto.SubjectClassDTO;
import com.linkdoan.backend.model.SubjectClass;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

public interface SubjectClassService {

    List<Map<String, Object>> getAll(String termId);

    Map<String, Object> getDetail(String subjectClassId);

    List<SubjectClass> create(List<SubjectClassDTO> subjectDTO);

    SubjectClassDTO updateOne(String subjectClassId, SubjectClassDTO subjectClassDTOList, String actionType, String username) throws FileNotFoundException;

    int update(List<SubjectClassDTO> subjectClassDTOList, String actionType, String username) throws FileNotFoundException;

    int delete(List<String> ids);

}
