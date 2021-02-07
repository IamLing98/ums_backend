package com.linkdoan.backend.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table
public class PrerequisitesSubject {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PrerequisitesSubject_Sequence")
    @SequenceGenerator(name = "id_Sequence", sequenceName = "ORACLE_DB_SEQ_ID")
    private Long id;

    @Column(name = "subject_id", columnDefinition = "VARCHAR(9)")
    private String subjectId;

    @Column(name = "prerequisites_subject_id", columnDefinition = "VARCHAR(9)")
    private String prerequisitesSubjectId;

}
