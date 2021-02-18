package com.linkdoan.backend.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name="invoice")
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="invoice_no")
    private Long invoiceNo;

    @Column(name="studentABN")
    private String  studentABN;

    @Column(name="invoice_created_date", columnDefinition = "DATETIME")
    private LocalDateTime invoiceCreatedDate;

    @Column(name="creator_id", columnDefinition = "DATETIME")
    private String creatorId;

    @Column(name="invoice_name")
    private String invoiceName;

    @Column(name="amount", columnDefinition = "DOUBLE")
    private Double amount;

}
