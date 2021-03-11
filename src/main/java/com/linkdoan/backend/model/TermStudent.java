package com.linkdoan.backend.model;

import com.linkdoan.backend.dto.ResultDTO;
import com.linkdoan.backend.dto.TermDTO;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "term_students")
public class TermStudent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @Column(name = "term_id")
    private String termId;

    @Column(name = "student_id")
    private String studentId;

    @Column(name = "GPA")
    private Double GPA;

    @Column(name = "diem_ren_luyen")
    private Integer diemRenLuyen;

    @Column(name = "rank")
    private Integer rank;

    public ResultDTO toDTO() {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setTermId(termId);
        resultDTO.setStudentId(studentId);
        resultDTO.setGPA(GPA);
        resultDTO.setId(id);
        resultDTO.setRank(rank);
        resultDTO.setDiemRenLuyen(diemRenLuyen);
        return resultDTO;
    }
}
