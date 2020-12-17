package com.linkdoan.backend.dto;

import com.linkdoan.backend.model.SubjectRegistration;
import lombok.Data;
import java.time.LocalDate;

@Data
public class SubjectRegistrationDTO {

    private Long id;

    private String term;

    private LocalDate date;

    private String subjectId;

    private String studentId;

    public SubjectRegistration toSubjectRegistrationModel(){
        SubjectRegistration subjectRegistration = new SubjectRegistration();
        subjectRegistration.setSubjectId(this.getSubjectId());
        return subjectRegistration;
    }
}
