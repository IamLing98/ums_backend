package com.linkdoan.backend.model;

import com.linkdoan.backend.dto.SubjectDTO;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "subject")
public class Subject {
    @Id
    @Column(name = "subject_id", unique = true, length = 9)
    private String subjectId;

    @Column(name = "subject_name")
    private String subjectName;

    @Column(name = "each_subject")
    private Integer eachSubject;

    @Column(name = "theory_number")
    private Integer theoryNumber;

    @Column(name = "exercise_number")
    private Integer exerciseNumber;

    @Column(name = "discuss_number")
    private Integer discussNumber;

    @Column(name = "self_learning_number")
    private Integer selfLearningNumber;

    @Column(name="practice_number")
    private Integer practiceNumber;

    @Column(name="subject_for_level")
    private Integer subjectForLevel;

    @Column(name="department_id", columnDefinition = "CHAR(7)")
    private String departmentId;

    public SubjectDTO toDTO(){
        SubjectDTO subjectDTO = new SubjectDTO();
        subjectDTO.setSubjectId(this.subjectId);
        subjectDTO.setSubjectName(this.subjectName);
        subjectDTO.setEachSubject(this.eachSubject);
        subjectDTO.setTheoryNumber(this.theoryNumber);
        subjectDTO.setExerciseNumber(this.exerciseNumber);
        subjectDTO.setDiscussNumber(this.discussNumber);
        subjectDTO.setSelfLearningNumber(this.selfLearningNumber);
        subjectDTO.setPracticeNumber(this.practiceNumber);
        subjectDTO.setSubjectForLevel(this.subjectForLevel);
        return subjectDTO;
    }
}
