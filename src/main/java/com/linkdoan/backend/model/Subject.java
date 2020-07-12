package com.linkdoan.backend.model;

import com.linkdoan.backend.dto.SubjectDTO;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name="subject")
public class Subject {
    @Id
    @Column(name="subject_id",unique = true,length = 9)
    private String subjectId;

    @Column(name="subject_name")
    private String subjectName;

    @Column(name="section_id")
    private String sectionId;

    @Column(name="type")//subject_type
    private String type;

    @Column(name="credit_number")
    private String creditNumber;

    @Column(name="theory_number")
    private String theoryNumber;

    @Column(name="practice_number")
    private String practiceNumber;

    public SubjectDTO toDTO(){
        SubjectDTO subjectDTO = new SubjectDTO();
        subjectDTO.setSubjectId(this.subjectId);
        subjectDTO.setSubjectName(this.subjectName);
        subjectDTO.setSectionId(this.sectionId);
        subjectDTO.setType(this.type);
        subjectDTO.setCreditNumber(this.creditNumber);
        subjectDTO.setTheoryNumber(this.theoryNumber);
        subjectDTO.setPracticeNumber(this.practiceNumber);
        return subjectDTO;
    }
}
