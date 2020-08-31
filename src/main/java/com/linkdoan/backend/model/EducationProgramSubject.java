package com.linkdoan.backend.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name="education_program_subject")
@Entity
public class EducationProgramSubject {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    @Column(name="subject_id")
    private String subjectId;

    @Column(name="education_program_id")
    private String educationProgramId;

    @Column(name = "subject_type")
    private Integer subjectType;

}
