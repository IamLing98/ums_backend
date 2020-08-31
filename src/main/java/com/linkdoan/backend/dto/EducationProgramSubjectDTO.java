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
    private String subjectId;
    private String educationProgramId;
    private String subjectType;
    public EducationProgramSubject toModel(){
        EducationProgramSubject educationProgramSubject = new EducationProgramSubject();
        educationProgramSubject.setSubjectType(NumberUtils.toInt(this.subjectType));
        educationProgramSubject.setEducationProgramId(this.educationProgramId);
        educationProgramSubject.setSubjectId(this.subjectId);
        return educationProgramSubject;
    }
}
