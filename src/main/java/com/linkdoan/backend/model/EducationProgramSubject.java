package com.linkdoan.backend.model;

import com.linkdoan.backend.dto.EducationProgramSubjectDTO;
import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "education_program_subject")
@Entity
public class EducationProgramSubject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "subject_id")
    private String subjectId;

    @Column(name = "education_program_id", columnDefinition = "CHAR(11)")
    private String educationProgramId;

    @Column(name = "transaction_type")
    private Integer transactionType = 0;

    @Column(name = "term")
    private Integer term = 0;

    public EducationProgramSubjectDTO toDTO() {
        EducationProgramSubjectDTO educationProgramSubjectDTO = new EducationProgramSubjectDTO();
        educationProgramSubjectDTO.setEducationProgramId(this.educationProgramId);
        educationProgramSubjectDTO.setTransactionType(this.transactionType);
//        educationProgramSubjectDTO.se
        return educationProgramSubjectDTO;
    }


}
