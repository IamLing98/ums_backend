package com.linkdoan.backend.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name="sub_fee_category")
public class SubFeeCategory {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="parent_fee_cetegory_id")
    private Long parentFeeCategoryId;

    @Column(name="sub_category_name")
    private String subCategoryName;

    @Column(name="description")
    private String description;

    //if is percent = 0, is correct value = 1
    @Column(name="value_type")
    private Integer valueType;

    @Column(name="value", columnDefinition = "DOUBLE")
    private Double value;
}
