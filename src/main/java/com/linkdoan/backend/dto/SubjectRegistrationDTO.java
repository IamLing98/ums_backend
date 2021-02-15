package com.linkdoan.backend.dto;

import com.linkdoan.backend.model.SubjectRegistration;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubjectRegistrationDTO {

    private Long id;

    private String studentId;

    private String subjectId;

    private String termId;

    private LocalDateTime date;

    private Integer autoSubmit;

    public SubjectRegistration toSubjectRegistrationModel() {
        SubjectRegistration subjectRegistration = new SubjectRegistration();
        subjectRegistration.setSubjectId(this.subjectId);
        subjectRegistration.setTermId(this.termId);
        subjectRegistration.setStudentId(this.studentId);
        subjectRegistration.setDate(this.date);
        subjectRegistration.setAutoSubmit(this.autoSubmit);
        return subjectRegistration;
    }

    public SubjectRegistrationDTO(String studentId, String subjectId, String termId, LocalDateTime date, Integer autoSubmit) {
        this.studentId = studentId;
        this.subjectId = subjectId;
        this.termId = termId;
        this.date = date;
        this.autoSubmit = autoSubmit;
    }
}
