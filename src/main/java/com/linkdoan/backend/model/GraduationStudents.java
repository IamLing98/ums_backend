package com.linkdoan.backend.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "graduation_students")
public class GraduationStudents {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="graduation_id")
    private Long graduationId;

    @Column(name="student_id")
    private String studentId;

    @Column(name="status")
    private Integer status;

    @Column(name="educationProgramId")
    private String educationProgramId;

    @Column(name="CPA")
    private Double CPA;

    @Column(name="rank")
    private Integer rank;
}
