package com.linkdoan.backend.model;

import com.linkdoan.backend.dto.TermDTO;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "term")
@Data
public class Term {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name="school_year_id")
    private String schoolYearId;

    @Column(name = "year")
    private Long year;

    @Column(name = "term")
    private Long term;

    @Column(name = "status")
    private Integer status;

    @Column(name = "progress")
    private Integer progress;

    @Column(name = "created_date")
    private LocalDate createdDate;

    @Column(name = "term_end_date")
    private LocalDate termEndDate;

    @Column(name = "subject_submitting_start_date")
    private LocalDateTime subjectSubmittingStartDate;

    @Column(name = "subject_submitting_end_date")
    private LocalDateTime subjectSubmittingEndDate;

    @Column(name = "subject_class_submitting_start_date")
    private LocalDateTime subjectClassSubmittingStartDate;

    @Column(name = "subject_class_submitting_end_date")
    private LocalDateTime subjectCLassSubmittingEndDate;

    @Column(name = "editSubmittingStartDate")
    private LocalDateTime editSubmittingStartDate;

    @Column(name = "editSubmittingEndDate")
    private LocalDateTime editSubmittingEndDate;

    @Column(name = "term_type")
    private Integer termType;

    @Column(name = "each_subject_fee")
    private Double eachSubjectFee;

    @Column(name = "multiple_number")
    private Double multipleNumber;

    @Column(name = "first_exam_fee")
    private Double firstExamFee;

    @Column(name = "second_exam_fee")
    private Double secondExamFee;

    //ngay thu hoc phi
    @Column(name = "tuition_fee_start_date")
    private LocalDateTime tuitionFeeStartDate;

    @Column(name = "tuition_fee_end_date")
    private LocalDateTime tuitionFeeEndDate;

    //ngay nhap diem
    @Column(name = "input_grade_start_date")
    private LocalDateTime inputGradeStartDate;

    @Column(name = "input_grade_end_date")
    private LocalDateTime inputGradeEndDate;

    //ngay chot diem
    @Column(name = "result_created_date")
    private LocalDateTime resultCreatedDate;

    @Column(name = "inFeeTotalValue")
    private Integer inFeeTotalValue = 0;

    @Column(name = "outFeeTotalValue")
    private Integer outFeeTotalValue = 0;


    public TermDTO toDTO() {
        TermDTO termDTO = new TermDTO();
        termDTO.setId(this.id);
        termDTO.setStatus(this.status);
        termDTO.setTerm(this.term);
        termDTO.setYear(this.year);
        termDTO.setProgress(this.progress);
        termDTO.setCreatedDate(this.createdDate);
        termDTO.setTermEndDate(this.termEndDate);
        termDTO.setSubjectSubmittingStartDate(this.subjectSubmittingStartDate);
        termDTO.setSubjectSubmittingEndDate(this.subjectSubmittingEndDate);
        termDTO.setSubjectClassSubmittingStartDate(this.subjectClassSubmittingStartDate);
        termDTO.setSubjectCLassSubmittingEndDate(this.subjectCLassSubmittingEndDate);
        termDTO.setEditSubmittingStartDate(this.editSubmittingStartDate);
        termDTO.setEditSubmittingEndDate(this.editSubmittingEndDate);
        return termDTO;
    }

}