package com.linkdoan.backend.dto;

import com.linkdoan.backend.model.EducationProgramSubject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EducationProgramSubjectDTO  {

    private String subjectId;

    private String educationProgramId;

    private Integer transactionType = 1;

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
