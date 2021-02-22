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
    private Long feeCategoryGroupId;

    //out is 0, and in is 1
    @Column(name = "fee_category_value")
    private Double value;

    @Column(name = "description")
    private String description;

    @Column(name = "frequency")
    private String frequency;

    @Column(name = "fee_category_abn")
    private String feeCategoryABN;

    public FeeCategory() {

    }
}
