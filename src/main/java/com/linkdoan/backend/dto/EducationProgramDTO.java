package com.linkdoan.backend.dto;

import com.linkdoan.backend.model.EducationProgram;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EducationProgramDTO  {

    private String educationProgramId;

    private String educationProgramName ;

    private String educationProgramLevel ;

    private String branchId="";

    private String educationProgramType ;

    private String educationProgramStatus ;

    private String branchName;

    private List<EducationProgramSubjectDTO> subjectList = new ArrayList<>();

    public EducationProgram toModel(){
        EducationProgram educationProgram = new EducationProgram();
        educationProgram.setBranchId(this.branchId);
        educationProgram.setEducationProgramLevel(NumberUtils.toInt(this.educationProgramLevel));
        educationProgram.setEducationProgramId(this.educationProgramId);
        educationProgram.setEducationProgramName(this.educationProgramName);
        educationProgram.setEducationProgramType(NumberUtils.toInt(this.educationProgramType));
        educationProgram.setEducationProgramStatus(NumberUtils.toInt(this.educationProgramStatus));
        return educationProgram;
    }

}
