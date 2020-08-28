package com.linkdoan.backend.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="selection_pool")
@Data
@Entity

public class SelectionPool {
    @Id
    @Column(name="selection_pool_id", columnDefinition = "CHAR(3)", unique = true)
    private String selectionPoolId;

    @Column(name = "first_subject_name", columnDefinition="CHAR(45)")
    private String firstSubjectName;

    @Column(name = "second_subject_name", columnDefinition="CHAR(45)")
    private String secondSubjectName;

    @Column(name = "third_subject_name", columnDefinition="CHAR(45)")
    private String thirdSubjectName;
}
