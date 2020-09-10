package com.linkdoan.backend.dto;

import com.linkdoan.backend.model.EducationProgramSubject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.math.NumberUtils;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EducationProgramSubjectDTO {
    private String id;
    private String educationProgramId;
    private String transactionType;
    private SubjectDTO subject;

    String getSubjectId (){
        return this.subject.getSubjectId();
    }
    
    public EducationProgramSubject toModel() {
        EducationProgramSubject educationProgramSubject = new EducationProgramSubject();
        educationProgramSubject.setTransactionType(NumberUtils.toInt(this.transactionType));
        educationProgramSubject.setEducationProgramId(this.educationProgramId);
        educationProgramSubject.setSubjectId(this.getSubjectId());
        return educationProgramSubject;
    }
}
