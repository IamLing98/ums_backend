package com.linkdoan.backend.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "fee_category")
public class FeeCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "fee_category_name")
    private String feeCategoryName;

    @Column(name = "fee_category_group_id")
    private Integer feeCategoryGroupId;

    //out is 0, and in is 1
    @Column(name = "fee_category_value")
    private Double value;

    //if is percent = 0, is correct value = 1, NONE value = 2
    @Column(name = "value_type", columnDefinition = "DOUBLE")
    private Integer valueType;

    @Column(name = "description")
    private String description;

    @Column(name = "frequency")
    private String frequency;

    @Column(name = "fee_category_abn")
    private String feeCategoryABN;

    //0 is in, 1 is out
    @Column(name = "fee_category_type")
    private Integer feeCategoryType;

    public FeeCategory() {

    }

    public FeeCategory(Long id, String feeCategoryName, Integer feeCategoryGroupId, Double value, Integer valueType, String description, String frequency, String feeCategoryABN, Integer feeCategoryType) {
        this.feeCategoryName = feeCategoryName;
        this.feeCategoryGroupId = feeCategoryGroupId;
        this.value = value;
        this.valueType = valueType;
        this.description = description;
        this.frequency = frequency;
        this.feeCategoryABN = feeCategoryABN;
        this.feeCategoryType = feeCategoryType;
        this.id = id;
    }
}
