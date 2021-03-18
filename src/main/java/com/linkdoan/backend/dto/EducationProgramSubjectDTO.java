package com.linkdoan.backend.dto;

import com.linkdoan.backend.model.EducationProgramSubject;
import com.poiji.annotation.ExcelCell;
import com.poiji.annotation.ExcelRow;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EducationProgramSubjectDTO {

    @ExcelRow
    private int rowIndex;

    @ExcelCell(0)
    private String subjectId;

    private String educationProgramId;

    private Integer transactionType = 1;

    @ExcelCell(14)
    private Integer term = 0;

    public EducationProgramSubject toModel() {
        EducationProgramSubject educationProgramSubject = new EducationProgramSubject();
        educationProgramSubject.setTransactionType(this.transactionType);
        educationProgramSubject.setEducationProgramId(this.educationProgramId);
        educationProgramSubject.setSubjectId(this.getSubjectId());
        educationProgramSubject.setTerm(this.term);
        return educationProgramSubject;
    }


}
