package com.linkdoan.backend.dto;

import com.linkdoan.backend.model.EducationProgram;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EducationProgramDTO {

    private String educationProgramId;

    private String educationProgramName;

    private Integer educationProgramLevel;

    private String branchId = "";

    private Integer educationProgramType;

    private Integer educationProgramStatus;

    private String branchName;

    private Integer totalTerm;

    private List<EducationProgramSubjectDTO> subjectList = new ArrayList<>();

    public EducationProgram toModel() {
        EducationProgram educationProgram = new EducationProgram();
        educationProgram.setBranchId(this.branchId);
        educationProgram.setEducationProgramLevel(this.educationProgramLevel);
        educationProgram.setEducationProgramId(this.educationProgramId);
        educationProgram.setEducationProgramName(this.educationProgramName);
        educationProgram.setEducationProgramType(this.educationProgramType);
        educationProgram.setEducationProgramStatus(this.educationProgramStatus);
        educationProgram.setTotalTerm(this.totalTerm);
        return educationProgram;
    }


    public EducationProgramDTO(String educationProgramId, String educationProgramName, Integer educationProgramLevel,
                               String branchId, Integer educationProgramType, Integer educationProgramStatus,
                               String branchName, Integer totalTerm) {
        this.educationProgramId = educationProgramId;
        this.educationProgramName = educationProgramName;
        this.educationProgramLevel = educationProgramLevel;
        this.branchId = branchId;
        this.educationProgramType = educationProgramType;
        this.educationProgramStatus = educationProgramStatus;
        this.branchName = branchName;
        this.totalTerm = totalTerm;
    }
}
