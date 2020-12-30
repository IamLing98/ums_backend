package com.linkdoan.backend.service;

import com.linkdoan.backend.dto.SubjectRegistrationDTO;
import com.linkdoan.backend.model.SubjectRegistration;
import com.linkdoan.backend.model.Term;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.List;

@ComponentScan(basePackages= {"com.linkdoan.backend.*"})
public interface SubjectRegistrationService {

    List<SubjectRegistration> getAll(String studentId);

    boolean addSubject(String studentId, SubjectRegistrationDTO subjectRegistrationDTO);

    int deleteSubject(String studentId, String subjectId, String termId);
}
