package com.linkdoan.backend.model;

import com.linkdoan.backend.dto.EducationProgramDTO;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name="education_program")
@Entity
public class EducationProgram {
    @Id
    @Column(name = "education_program_id", columnDefinition = "CHAR(11)", unique = true)
    private String educationProgramId;

    @Column(name = "education_program_name", columnDefinition = "VARCHAR(100)")
    private String educationProgramName;

    @Column(name = "education_program_level", columnDefinition = "INT")
    private Integer educationProgramLevel;

    @Column(name = "education_program_type", columnDefinition = "INT")
    private Integer educationProgramType;

    @Column(name = "branch_id", columnDefinition="CHAR(10)")
    private String branchId;

    @Column(name = "education_program_status", columnDefinition = "INT")
    private Integer educationProgramStatus;

    @Column(name = "total_term", columnDefinition = "INT")
    private Integer totalTerm = 8;

    public EducationProgramDTO toDTO(){
        EducationProgramDTO educationProgramDTO = new EducationProgramDTO();
        educationProgramDTO.setBranchId(this.branchId);
        educationProgramDTO.setEducationProgramId(this.educationProgramId);
        educationProgramDTO.setEducationProgramLevel(this.educationProgramLevel);
        educationProgramDTO.setEducationProgramName(this.educationProgramName);
        educationProgramDTO.setEducationProgramStatus(this.educationProgramStatus);
        educationProgramDTO.setEducationProgramType(this.educationProgramType);
        return educationProgramDTO;
    }
}
