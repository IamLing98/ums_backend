package com.linkdoan.backend.dto;

import com.linkdoan.backend.model.EducationProgramSubject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EducationProgramSubjectDTO  {

    private String subjectId;

    private String educationProgramId;

    private String subjectName;

    private Integer eachSubject;

    private Integer theoryNumber;

    private Integer exerciseNumber;

    private Integer discussNumber;

    private Integer selfLearningNumber;

    private Integer practiceNumber;

    private Integer subjectForLevel;

    private List<SubjectDTO> prerequisitesSubjects ;

    private Integer transactionType = 0;

    private Integer term = 0;

    public EducationProgramSubject toModel() {
        EducationProgramSubject educationProgramSubject = new EducationProgramSubject();
        educationProgramSubject.setTransactionType(this.transactionType);
        educationProgramSubject.setEducationProgramId(this.educationProgramId);
        educationProgramSubject.setSubjectId(this.getSubjectId());
        return educationProgramSubject;
    }

    public EducationProgramSubjectDTO(String subjectId, String subjectName, Integer eachSubject, Integer theoryNumber,
                                      Integer exerciseNumber, Integer discussNumber, Integer selfLearningNumber, Integer practiceNumber,
                                      Integer subjectForLevel,   Integer transactionType, Integer term) {
        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.eachSubject = eachSubject;
        this.theoryNumber = theoryNumber;
        this.exerciseNumber = exerciseNumber;
        this.discussNumber = discussNumber;
        this.selfLearningNumber = selfLearningNumber;
        this.practiceNumber = practiceNumber;
        this.subjectForLevel = subjectForLevel;
        this.prerequisitesSubjects = prerequisitesSubjects;
        this.educationProgramId = educationProgramId;
        this.transactionType = transactionType;
        this.term = term;
    }
}
