package com.linkdoan.backend.model;

import lombok.Data;
import lombok.Generated;

import javax.persistence.*;

@Entity
@Data
@Table(name="fee_category")
public class FeeCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="category_name")
    private String feeCategoryName;

    @Column(name="category_type")
    private Integer categoryType;

    @Column(name="category_value")
    private Long value;
}
