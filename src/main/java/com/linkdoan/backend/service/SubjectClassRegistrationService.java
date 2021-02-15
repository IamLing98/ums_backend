package com.linkdoan.backend.service;

import com.linkdoan.backend.dto.SubjectClassRegistrationDTO;
import com.linkdoan.backend.model.SubjectClassRegistration;

import java.util.List;
import java.util.Map;

public interface SubjectClassRegistrationService {

    //admin role
    boolean subjectClassSubmitForNewStudent(String termId );

    SubjectClassRegistration submit(String studentId, SubjectClassRegistrationDTO subjectClassRegistrationDTO);

    Map<String, Object>  getListSubmitted(String student, String termId);

    boolean delete(String studentId, String subjectClassId, String scheduleId);

}
