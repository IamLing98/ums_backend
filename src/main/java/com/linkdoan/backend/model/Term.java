package com.linkdoan.backend.model;

import com.linkdoan.backend.dto.TermDTO;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "term")
@Data
public class Term {

    @Id
    @Column(name = "id")
    private String id;

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
    private LocalDate subjectSubmittingStartDate;

    @Column(name = "subject_submitting_end_date")
    private LocalDate subjectSubmittingEndDate;

    @Column(name = "subject_class_submitting_start_date")
    private LocalDate subjectClassSubmittingStartDate;

    @Column(name = "subject_class_submitting_end_date")
    private LocalDate subjectCLassSubmittingEndDate;

    @Column(name = "editSubmittingStartDate")
    private LocalDate editSubmittingStartDate;

    @Column(name = "editSubmittingEndDate")
    private LocalDate editSubmittingEndDate;

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