package com.linkdoan.backend.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "teacher_lesson_date")
public class TeacherLessonDate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "teacher_id")
    private String teacherId;

    @Column(name = "subject_class_id")
    private String subjectClassId;

    @Column(name = "lesson_number")
    private Long lessonNumber;

    @Column(name = "lesson_date")
    private LocalDate lessonDate;

    @Column(name = "status")
    private Integer status;

    @Column(name = "lessonDateReplace")
    private LocalDate lessonDateReplace;


}
