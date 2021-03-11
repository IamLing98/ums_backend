package com.linkdoan.backend.dto;

import com.linkdoan.backend.model.Term;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TermDTO {
    private String id;

    private Long year;

    private Long term;

    private Integer status;

    private Integer progress;

    private LocalDate createdDate;

    private LocalDate termEndDate;

    private LocalDateTime subjectSubmittingStartDate;

    private LocalDateTime subjectSubmittingEndDate;

    private LocalDateTime subjectClassSubmittingStartDate;

    private LocalDateTime subjectCLassSubmittingEndDate;

    private LocalDateTime editSubmittingStartDate;

    private LocalDateTime editSubmittingEndDate;

    private String actionType;

    private Double eachSubjectFee;

    private Double multipleNumber;

    private Double firstExamFee;

    private Double secondExamFee;

    private LocalDateTime tuitionFeeStartDate;

    private LocalDateTime tuitionFeeEndDate;

    private Integer inFeeTotalValue = 0;

    private Integer outFeeTotalValue = 0;

    private LocalDateTime inputGradeStartDate;

    private LocalDateTime inputGradeEndDate;


    public Term toModel() {
        Term term = new Term();
        term.setId(this.id);
        term.setStatus(this.status);
        term.setTerm(this.term);
        term.setYear(this.year);
        term.setProgress(this.progress);
        term.setCreatedDate(this.createdDate);
        term.setTermEndDate(this.termEndDate);
        term.setSubjectSubmittingStartDate(this.subjectSubmittingStartDate);
        term.setSubjectSubmittingEndDate(this.subjectSubmittingEndDate);
        term.setSubjectClassSubmittingStartDate(this.subjectClassSubmittingStartDate);
        term.setSubjectCLassSubmittingEndDate(this.subjectCLassSubmittingEndDate);
        term.setEditSubmittingStartDate(this.editSubmittingStartDate);
        term.setEditSubmittingEndDate(this.editSubmittingEndDate);
        return term;
    }
}
