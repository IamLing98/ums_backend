package com.linkdoan.backend.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "student_subject")
public class StudentSubject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "student_id")
    private String studentId;

    @Column(name = "education_program_id")
    private String educationProgramId;

    @Column(name = "subject_id")
    private String subjectId;

    @Column(name = "diem_chuyen_can", columnDefinition = "INT")
    private Double diemChuyenCan;

    @Column(name = "diem_bai_tap", columnDefinition = "INT")
    private Double diemBaiTap;

    @Column(name = "diem_kiem_tra", columnDefinition = "INT")
    private Double diemKiemTra;

    @Column(name = "diem_thi", columnDefinition = "INT")
    private Double diemThi;

    @Column(name = "diem_thi_lai", columnDefinition = "INT")
    private Double diemThiLai;

    @Column(name = "diem_trung_binh", columnDefinition = "INT")
    private Double diemTrungBinh;

    @Column(name = "diem_thang_bon")
    private Double diemThangBon;

    @Column(name = "diem_chu", columnDefinition = "CHAR(2)")
    private String diemChu;
}
