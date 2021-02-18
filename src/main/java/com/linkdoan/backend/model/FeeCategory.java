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

    //out is 0, and in is 1
    @Column(name="category_value")
    private Double value;

    //has child is 1, no children is 0
    @Column(name="has_children")
    private Integer hasChildren;

    //if is percent = 0, is correct value = 1
    @Column(name="value_type" ,columnDefinition = "DOUBLE")
    private Integer valueType;

    @Column(name="description")
    private String description;
}
