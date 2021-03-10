package com.linkdoan.backend.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="term_students")
public class TermStudent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    Long id;

    @Column(name="term_id")
    private String termId;

    @Column(name="student_id")
    private String studentId;

    @Column(name="GPA")
    private Double GPA;

    @Column(name="diem_ren_luyen")
    private Double diemRenLuyen;
}
