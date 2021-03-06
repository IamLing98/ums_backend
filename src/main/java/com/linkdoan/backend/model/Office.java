package com.linkdoan.backend.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "office")
@Entity
public class Office {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "office_name")
    private String officeName;
}
