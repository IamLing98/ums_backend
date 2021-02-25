package com.linkdoan.backend.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name="student_fee_categories")
public class StudentsFeeCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="student_id")
    private String studentId;

    @Column(name="term_id")
    private String termId;

    @Column(name="fee_categories_id")
    private Long feeCategoriesId;

    @Column(name="is_paid")
    private Integer isPaid = 0;

    @Column(name="transaction_date", columnDefinition = "DATETIME")
    private LocalDateTime transactionDate ;

    @Column(name="value")
    private Long value;
}
