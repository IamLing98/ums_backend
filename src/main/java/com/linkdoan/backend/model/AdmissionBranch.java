package com.linkdoan.backend.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name="admission_branch")
@Entity

public class AdmissionBranch {
    @Id
    @Column(name = "admissions_id")
    private Long admissionsId;

    @Column(name = "branch_id", columnDefinition = "CHAR(10)")
    private String brachId;
}
