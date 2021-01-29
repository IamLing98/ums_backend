package com.linkdoan.backend.dto;

import com.linkdoan.backend.model.Term;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

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

    private LocalDate subjectSubmittingStartDate;

    private LocalDate subjectSubmittingEndDate;

    private LocalDate subjectClassSubmittingStartDate;

    private LocalDate subjectCLassSubmittingEndDate;

    private LocalDate editSubmittingStartDate;

    private LocalDate editSubmittingEndDate;

    private String actionType;

    private Long activeSchedule;

    public Term toModel(){
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
