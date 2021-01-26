package com.linkdoan.backend.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name="schedule_subject_class")
@Entity
public class ScheduleSubjectClass {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "schedule_Sequence")
    @SequenceGenerator(name = "id_Sequence", sequenceName = "ORACLE_DB_SEQ_ID")
    private Long id;

    @Column(name="schedule_id")
    private Long scheduleId;

    @Column(name="term_id")
    private String termId;

    @Column(name="subject_class_id")
    private String subjectClassId;

    @Column(name="room_id")
    private String roomId;

    @Column(name="day_of_week")
    private Integer dayOfWeek;

    @Column(name="hour_of_day")
    private Integer hourOfDay;
}
