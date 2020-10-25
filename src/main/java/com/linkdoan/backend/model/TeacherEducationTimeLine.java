package com.linkdoan.backend.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name="teacher_time_line")
@Entity
public class TeacherEducationTimeLine {

    @Id
    @Column(name = "teacher_time_line_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer teacherTimeLineId;

    @Column(name="teacher_id")
    private String teacherId;

    @Column(name="graduation_year")
    private String graduationYear;

    @Column(name="education_level")
    private Integer educationLevel;

    @Column(name="branch_name")
    private String banchName;

    @Column(name="educationPlace")
    private String educationPlace;


}
