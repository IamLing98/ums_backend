package com.linkdoan.backend.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Table(name="schedule")
@Entity
public class Schedule {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "schedule_Sequence")
    @SequenceGenerator(name = "id_Sequence", sequenceName = "ORACLE_DB_SEQ_ID")
    private Long id;

    @Column(name="term_id")
    private String termId;

    @Column(name="created_data")
    private LocalDate createdDate;




}
