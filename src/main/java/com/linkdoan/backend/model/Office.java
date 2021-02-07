package com.linkdoan.backend.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "office")
public class Office {
    @Id
    @Column(name = "office_id", unique = true, columnDefinition = "CHAR(7)")
    private String officeId;

    @Column(name = "office_name", columnDefinition = "VARCHAR(245)")
    private String officeName;

    @Column(name = "description", columnDefinition = "VARCHAR(245)")
    private String description;
}
