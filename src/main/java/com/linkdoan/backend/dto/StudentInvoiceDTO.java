package com.linkdoan.backend.dto;

import com.linkdoan.backend.model.Student;
import com.linkdoan.backend.model.StudentsFeeCategory;
import com.linkdoan.backend.model.Term;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentInvoiceDTO {

    private Long invoiceNo;

    private Student student;

    private LocalDateTime invoiceCreatedDate;

    private String creatorId;

    private String invoiceName;

    private Double amount;

    private String textMoney;

    private Long reasonId;

    private Term term;

    private Integer invoiceType;

    private String note;

    private List<StudentsFeeCategory> studentsFeeCategories;

    private List<FeeCategoryDTO> items;
}
