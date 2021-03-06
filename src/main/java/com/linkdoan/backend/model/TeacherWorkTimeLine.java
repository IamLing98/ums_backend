package com.linkdoan.backend.model;


import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "teacher_work_timeline")
@Entity
public class TeacherWorkTimeLine {

    @Id
    @Column(name = "teacher_work_timeline_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer teacherWorkTimeLineId;

    @Column(name = "teacher_id")
    private String teacherId;

    @Column(name = "start_date")
    private Integer startDate;

    @Column(name = "end_date")
    private Integer endDate;

    @Column(name = "job")
    private String job;

    @Column(name = "work_unit")
    private String workUnit;

}
