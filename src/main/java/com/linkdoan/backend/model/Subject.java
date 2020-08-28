package com.linkdoan.backend.model;

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
    private Integer selfLeaningNumber;

    @Column(name = "prerequisite_subject_id")
    private Integer prerequisiteSubjectId;

}
