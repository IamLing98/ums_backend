package com.linkdoan.backend.service;

import com.linkdoan.backend.dto.SubjectClassDTO;
import com.linkdoan.backend.dto.SubjectClassRegistrationDTO;
import com.linkdoan.backend.model.ScheduleSubjectClass;
import com.linkdoan.backend.model.SubjectClassRegistration;

import java.util.List;

public interface SubjectClassRegistrationService {

    //admin role
    boolean subjectClassSubmitForNewStudent(String termId, Long scheduleId);

    SubjectClassRegistration submit(String studentId, SubjectClassRegistrationDTO subjectClassRegistrationDTO);

    List<SubjectClassDTO> getListSubmitted(String student, String termId);

    boolean delete(String studentId, String subjectClassId, Long scheduleId);

}
