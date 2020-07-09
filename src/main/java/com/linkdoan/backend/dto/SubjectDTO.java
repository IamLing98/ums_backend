package com.linkdoan.backend.dto;

import com.linkdoan.backend.base.anotation.AdjHistory;
import com.linkdoan.backend.base.dto.SystemDTO;
import com.linkdoan.backend.model.Subject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubjectDTO extends SystemDTO {

    private String subjectId;

    @AdjHistory(field = "subject_name")
    private String subjectName;

    @AdjHistory(field = "section_id")
    private String sectionId;

    @AdjHistory(field = "type")
    private String type;

    @AdjHistory(field = "credit_number")
    private String creditNumber;

    @AdjHistory(field = "theory_number")
    private String theoryNumber;

    @AdjHistory(field = "practice_number")
    private String practiceNumber;
    public Subject toModel(){
        Subject subject = new Subject();
        subject.setSubjectId(this.subjectId);
        subject.setSubjectName(this.subjectName);
        subject.setSectionId(this.sectionId);
        subject.setType(this.type);
        subject.setCreditNumber(this.creditNumber);
        subject.setTheoryNumber(this.theoryNumber);
        subject.setPracticeNumber(this.practiceNumber);
        return subject;
    }
}
