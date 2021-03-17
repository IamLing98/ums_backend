package com.linkdoan.backend.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "bonus_percent_value")
public class BonusPercentValue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "bonus_percent_name")
    private String bonusExactName;

    @Column(name = "value")
    private Double value;
}