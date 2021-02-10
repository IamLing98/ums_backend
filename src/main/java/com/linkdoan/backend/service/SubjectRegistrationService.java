package com.linkdoan.backend.service;

import com.linkdoan.backend.dto.SubjectDTO;
import com.linkdoan.backend.dto.SubjectRegistrationDTO;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;
import java.util.Map;

@ComponentScan(basePackages = {"com.linkdoan.backend.*"})
public interface SubjectRegistrationService {

    //admin role

    List<Map<String, Object>> getSubmittingInfo(String termId);

    List<SubjectDTO> getListSubjectSubmitted(String termId, String studentId);

    boolean subjectSubmitForNewStudent(String termId);

    boolean addSubject(String studentId, SubjectRegistrationDTO subjectRegistrationDTO);

    int deleteSubject(String studentId, String subjectId, String termId);
}
