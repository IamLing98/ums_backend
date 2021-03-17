package com.linkdoan.backend.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "contract_exact_bonus")
@Entity
public class ContractExactBonus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "contract_id")
    private Long contractId;

    @Column(name = "bonus_id")
    private Long bonusId;

    @Column(name = "value")
    private Double value;
}
