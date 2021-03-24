package com.linkdoan.backend.dto;

import com.linkdoan.backend.model.Subject;
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

    private Subject subject;

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

    public StudentSubjectDTO(Long id, String studentId, String educationProgramId, String subjectId, Subject subject, Integer eachSubject, Double diemChuyenCan, Double diemBaiTap, Double diemKiemTra, Double diemThi, Double diemThiLai, Double diemTrungBinh, Double diemThangBon, String diemChu) {
        this.id = id;
        this.studentId = studentId;
        this.educationProgramId = educationProgramId;
        this.subjectId = subjectId;
        this.subject = subject;
        this.eachSubject = eachSubject;
        this.diemChuyenCan = diemChuyenCan;
        this.diemBaiTap = diemBaiTap;
        this.diemKiemTra = diemKiemTra;
        this.diemThi = diemThi;
        this.diemThiLai = diemThiLai;
        this.diemTrungBinh = diemTrungBinh;
        this.diemThangBon = diemThangBon;
        this.diemChu = diemChu;
    }
}
