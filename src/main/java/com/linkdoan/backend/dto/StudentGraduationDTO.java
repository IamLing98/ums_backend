package com.linkdoan.backend.dto;

import com.linkdoan.backend.model.EducationProgram;
import com.linkdoan.backend.model.Student;
import com.linkdoan.backend.model.StudentGraduation;
import com.linkdoan.backend.model.Term;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StudentGraduationDTO {

    private Long id;

    private String studentId;

    private Student student;

    private String termId;

    private Term term;

    private Integer status;

    private String educationProgramId;

    private EducationProgram educationProgram;

    private Double CPA;

    private Integer rankValue;

    private Integer totalEachSubject;

    public StudentGraduationDTO(Long id, String studentId, Student student, String termId, Term term, Integer status, String educationProgramId, EducationProgram educationProgram, Double CPA, Integer rankValue, Integer totalEachSubject) {
        this.id = id;
        this.studentId = studentId;
        this.student = student;
        this.termId = termId;
        this.term = term;
        this.status = status;
        this.educationProgramId = educationProgramId;
        this.educationProgram = educationProgram;
        this.CPA = CPA;
        this.rankValue = rankValue;
        this.totalEachSubject = totalEachSubject;
    }

    public StudentGraduation toModel() {
        StudentGraduation studentGraduation = new StudentGraduation();
        studentGraduation.setId(id);
        studentGraduation.setStudentId(studentId);
        studentGraduation.setTermId(termId);
        studentGraduation.setStatus(status);
        studentGraduation.setEducationProgramId(educationProgramId);
        studentGraduation.setCPA(CPA);
        studentGraduation.setRankValue(rankValue);
        studentGraduation.setTotalEachSubject(totalEachSubject);
        return studentGraduation;
    }
}
