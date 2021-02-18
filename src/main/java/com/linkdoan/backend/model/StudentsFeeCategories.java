package com.linkdoan.backend.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="student_fee_categories")
public class StudentsFeeCategories {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="student_id")
    private String studentId;

    @Column(name="fee_categories_id")
    private Long feeCategoriesId;

    @Column(name="is_paid")
    private Integer isPaid = 0;
}
