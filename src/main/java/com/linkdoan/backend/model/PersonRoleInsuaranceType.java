package com.linkdoan.backend.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name="person_role_insuarance_type")
@Entity
public class PersonRoleInsuaranceType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="role_id")
    private Long roleId;

    @Column(name="role_name")
    private String roleName;

    @Column(name="insurance_type_id")
    private Long insuranceTypeId;

    @Column(name="support_value")
    private Double supportValue;

}
