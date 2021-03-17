package com.linkdoan.backend.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "bonus_exact_value")
public class BonusExactValue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "bonus_exact_name")
    private String bonusExactName;

    @Column(name = "value")
    private Double value;
}
