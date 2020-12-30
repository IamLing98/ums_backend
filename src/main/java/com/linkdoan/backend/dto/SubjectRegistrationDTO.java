package com.linkdoan.backend.dto;

import com.linkdoan.backend.model.SubjectRegistration;
import lombok.Data;

import javax.persistence.Column;
import java.time.LocalDate;

@Data
public class SubjectRegistrationDTO {

    private Long id;

    private String studentId;

    private String subjectId;

    private String termId;

    private LocalDate date;

    public SubjectRegistration toSubjectRegistrationModel(){
        SubjectRegistration subjectRegistration = new SubjectRegistration();
        subjectRegistration.setSubjectId(this.getSubjectId());
        subjectRegistration.setTermId(this.termId);
        subjectRegistration.setStudentId(this.studentId);
        subjectRegistration.setDate(this.date);
        return subjectRegistration;
    }
}
