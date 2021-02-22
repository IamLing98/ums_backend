package com.linkdoan.backend.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="fee_category_group")
public class FeeCategoryGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    Long id;

    @Column(name="fee_category_group_name")
    private String feeCategoryGroupName;

    //0 is in, 1 is out
    @Column(name="fee_category_group_type")
    private Integer feeCategoryGroupType;

    @Column(name="fee_category_group_type_ABN")
    private String feeCategoryGroupTypeABN;

    @Column(name="fee_category_group_role")
    private String role;

}
