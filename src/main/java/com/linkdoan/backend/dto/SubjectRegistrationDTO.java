package com.linkdoan.backend.dto;

import com.linkdoan.backend.model.SubjectRegistration;
import lombok.Data;
import java.time.LocalDate;

@Data
public class SubjectRegistrationDTO extends SubjectDTO{

    private Integer id;

    private String term;

    private LocalDate date;

    public SubjectRegistrationDTO(String subjectId, String subjectName, Integer eachSubject, Integer theoryNumber, Integer exerciseNumber,
                                  Integer discussNumber, Integer selfLearningNumber, Integer practiceNumber, Integer subjectForLevel, Integer id,
                                  String term, LocalDate date) {
        super(subjectId, subjectName, eachSubject, theoryNumber, exerciseNumber, discussNumber, selfLearningNumber, practiceNumber, subjectForLevel);
        this.id = id;
        this.term = term;
        this.date = date;
    }

    public SubjectRegistration toSubjectRegistrationModel(){
        SubjectRegistration subjectRegistration = new SubjectRegistration();
        subjectRegistration.setSubjectId(this.getSubjectId());
        return subjectRegistration;
    }
}
