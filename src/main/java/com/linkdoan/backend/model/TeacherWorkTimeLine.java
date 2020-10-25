package com.linkdoan.backend.model;


import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Table(name = "teacher_work_timeline")
@Entity
public class TeacherWorkTimeLine {

    @Id
    @Column(name = "teacher_work_timeline_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer teacherWorkTimeLineId;

    @Column(name="teacher_id")
    private String teacherId;

    @Column(name="start_date")
    private LocalDate startDate;

    @Column(name="end_date")
    private LocalDate endDate;

    @Column(name="job")
    private String job;

}
