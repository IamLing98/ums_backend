package com.linkdoan.backend.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Data
@Table(name="branch")
@Entity

public class Branch {

    @Id
    @Size(min=10, max=10)
    @Column(name = "branch_id",unique = true,columnDefinition="CHAR(10)")
    private String branchId;

    @Column(name = "branch_name", columnDefinition="CHAR(45)")
    private String branchName;

    @Column(name = "department_id", columnDefinition="CHAR(7)")
    private String departmentId;
}
