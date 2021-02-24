package com.linkdoan.backend.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "ffe_reason")
@Entity
public class FeeReason {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "reason_name")
    private String reasonName;

    @Column(name = "reason_for_type")
    private Long reasonType;
}
