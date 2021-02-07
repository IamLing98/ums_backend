package com.linkdoan.backend.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;


@Entity
@Table(name = "admissions")
@Data
public class Admissions {

    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO,
            generator = "native"
    )
    @Column(name = "admissions_id", unique = true)
    private Long admissionsId;

    @Column(name = "admission_type_id")
    private String admissionTypeId;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "admission_limit")
    private Integer admissionLimit;

    @Column(name = "status")
    private Integer status;
}
