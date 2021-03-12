package com.linkdoan.backend.dto;

import com.linkdoan.backend.model.Department;
import com.linkdoan.backend.model.Student;
import com.linkdoan.backend.model.TermStudent;
import com.linkdoan.backend.model.YearClass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultDTO {

    Long id;

    String termId;

    String studentId;

    Student student;

    Double GPA;

    Integer diemRenLuyen;

    Integer rank;

    YearClass yearClass;

    Department department;

    public TermStudent toModel() {
        TermStudent termStudent = new TermStudent();
        termStudent.setRank(rank);
        termStudent.setGPA(GPA);
        termStudent.setDiemRenLuyen(diemRenLuyen);
        termStudent.setTermId(termId);
        termStudent.setStudentId(studentId);
        return termStudent;
    }
}
