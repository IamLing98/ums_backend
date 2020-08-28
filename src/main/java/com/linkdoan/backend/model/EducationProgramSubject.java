package com.linkdoan.backend.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name="education_program_subject")
@Entity
public class EducationProgramSubject {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "subjectId")
    private Subject subject;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "education_program_id")
    private EducationProgram educationProgram;

    @Column(name = "subject_type")
    private Integer subjectType;

}
