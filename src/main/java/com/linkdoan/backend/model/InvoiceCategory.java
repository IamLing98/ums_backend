package com.linkdoan.backend.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="invoice_category")
public class InvoiceCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="invoice_no")
    private Long invoiceNo;

    @Column(name="category_id")
    private Long categoryId;



}
