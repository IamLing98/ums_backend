package com.linkdoan.backend.model;

import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@Table(name="graduation")
public class Graduation {

    @Column(name="id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="school_year_id")
    private String schoolYearId;

    @Column(name="number")
    private Integer number;

}
