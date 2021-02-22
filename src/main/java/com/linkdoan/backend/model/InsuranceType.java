package com.linkdoan.backend.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="insuarance_type")
public class InsuranceType {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="insurance_type_name")
    private String insuranceTypeName;

    @Column(name="value_type")
    private Integer value_type;

    @Column(name="person_real_value")
    private Double personRealValue;

    @Column(name="enterprise_real_value")
    private Double enterpriseRealValue;

    @Column(name="person_coefficient_value")
    private Double personCoefficientValue;

    @Column(name="enterprise_coefficient_value")
    private Double enterpriseCoefficientValue;

}
