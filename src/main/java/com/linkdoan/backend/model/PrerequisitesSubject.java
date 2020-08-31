package com.linkdoan.backend.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table
public class PrerequisitesSubject {

    @Id
    @Column(unique = true)
    private Long id;

    @Column(name="subject_id", columnDefinition = "VARCHAR(9)")
    private String subjectId;

    @Column(name="prerequisites_subject_id", columnDefinition = "VARCHAR(9)")
    private String prerequisitesSubjectId;

}
