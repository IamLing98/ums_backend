package com.linkdoan.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentSubjectDTO {

    private Long id;

    private String studentId;

    private String educationProgramId;

    private String subjectId;

    private String subjectName;

    private Integer eachSubject;

    private Double diemChuyenCan;

    private Double diemBaiTap;

    private Double diemKiemTra;

    private Double diemThi;

    private Double diemThiLai;

    private Double diemTrungBinh;

    private Double diemThangBon;

    private String diemChu;

    public StudentSubjectDTO(Long id, String studentId, String educationProgramId, String subjectId, String subjectName, Integer eachSubject, Double diemTrungBinh, Double diemThangBon, String diemChu) {
        this.id = id;
        this.studentId = studentId;
        this.educationProgramId = educationProgramId;
        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.eachSubject = eachSubject;
        this.diemTrungBinh = diemTrungBinh;
        this.diemThangBon = diemThangBon;
        this.diemChu = diemChu;
    }
}
