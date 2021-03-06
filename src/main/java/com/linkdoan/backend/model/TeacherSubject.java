package com.linkdoan.backend.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "teacher_subject")
@Entity
public class TeacherSubject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "teacher_id")
    private String teacherId;

    @Column(name = "subject_id")
    private String subjectId;
}
