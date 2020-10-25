package com.linkdoan.backend.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "config_variable")
public class ConfigVariable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "variable_name")
    private String variableName;

    @Column(name = "variable_value")
    private String variableValue;
}
