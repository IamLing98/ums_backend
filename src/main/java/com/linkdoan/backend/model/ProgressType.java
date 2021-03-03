package com.linkdoan.backend.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "progress_type")
@Entity
@Data
public class ProgressType {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "progress_name")
    private String progressName;
}
