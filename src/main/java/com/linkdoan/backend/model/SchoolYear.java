package com.linkdoan.backend.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "school_year")
public class SchoolYear {

    @Id
    @Column(name = "id")
    private String schoolYear;

    @Column(name = "start")
    private Integer start;

    @Column(name = "end")
    private Integer end;
}
