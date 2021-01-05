package com.linkdoan.backend.service;

import com.linkdoan.backend.dto.SubjectDTO;
import com.linkdoan.backend.dto.SubjectRegistrationDTO;
import com.linkdoan.backend.model.SubjectRegistration;
import com.linkdoan.backend.model.Term;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@ComponentScan(basePackages= {"com.linkdoan.backend.*"})
public interface SubjectRegistrationService {

    //admin role

    List<Map<String, Object>> getSubmittingInfo(String termId);

    List<SubjectDTO> getListSubjectSubmitted(String termId, String studentId);

    boolean subjectSubmitForNewStudent(String termId);

    boolean addSubject(String studentId, SubjectRegistrationDTO subjectRegistrationDTO);

    int deleteSubject(String studentId, String subjectId, String termId);
}
