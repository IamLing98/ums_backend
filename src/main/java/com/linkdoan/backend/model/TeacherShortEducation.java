package com.linkdoan.backend.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "teacher_short_education")

public class TeacherShortEducation {

    @Id
    @Column(name = "teacher_short_education_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer teacherTimeLineId;

    @Column(name="teacher_id")
    private String teacherId;

    @Column(name="year")
    private Integer year;

    @Column(name="content")
    private String content;

    @Column(name="education_place")
    private String educationPlace;


}
