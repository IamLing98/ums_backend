package com.linkdoan.backend.model;

import com.linkdoan.backend.dto.StudentGraduationDTO;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "students_graduation")
public class StudentGraduation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "student_id")
    private String studentId;

    @Column(name = "term_id")
    private String termId;

    @Column(name = "status")
    private Integer status;

    @Column(name = "educationProgramId")
    private String educationProgramId;

    @Column(name = "CPA")
    private Double CPA;

    @Column(name = "rank_value")
    private Integer rankValue;

    @Column(name = "total_each_subject")
    private Integer totalEachSubject;

    public StudentGraduationDTO toDTO() {
        StudentGraduationDTO studentGraduation = new StudentGraduationDTO();
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
