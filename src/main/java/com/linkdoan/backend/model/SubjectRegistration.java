package com.linkdoan.backend.model;

import lombok.Data;

import javax.persistence.*;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "subject_registration")
public class SubjectRegistration {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "student_id", columnDefinition="CHAR(9)")
    private String studentId;

    @Column(name = "subject_id", columnDefinition = "CHAR(9)")
    private String subjectId;

    @Column(name = "term_id",columnDefinition = "CHAR(6)")
    private String termId;

    @Column(name = "date")
    private LocalDate date;
}
