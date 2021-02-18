package com.linkdoan.backend.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="subject_class_seq")
public class SubjectClassSeq {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="termId")
    private String termId;

    @Column(name="subject_id")
    private String subjectId;

    @Column(name="next_val")
    private Long nextVal = 0L;
}
