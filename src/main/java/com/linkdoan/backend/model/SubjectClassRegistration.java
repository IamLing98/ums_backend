package com.linkdoan.backend.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "subject_class_registration")
public class SubjectClassRegistration {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "subject_class_reg_sequence")
    @SequenceGenerator(name = "subject_class_reg_sequence", sequenceName = "ORACLE_DB_SEQ_ID")
    private Long id;

    @Column(name = "student_id", columnDefinition = "CHAR(9)")
    private String studentId;

    @Column(name = "subject_class_id", columnDefinition = "CHAR(14)")
    private String subjectClassId;

    @Column(name = "term_id", columnDefinition = "CHAR(6)")
    private String termId;

    @Column(name = "submitted_date", columnDefinition = "DATETIME")
    private LocalDateTime submittedDate;

    @Column(name = "auto_submit", columnDefinition = "INT")
    private Integer autoSubmit;

    @Column(name = "status", columnDefinition = "INT")
    private Integer status;

    @Column(name = "diem_chuyen_can", columnDefinition = "INT")
    private Integer diemChuyenCan;

    @Column(name = "diem_bai_tap", columnDefinition = "INT")
    private Integer diemBaiTap;

    @Column(name = "diem_kiem_tra", columnDefinition = "INT")
    private Integer diemKiemTra;

    @Column(name = "diem_thi", columnDefinition = "INT")
    private Integer diemThi;

    @Column(name = "diem_thi_lai", columnDefinition = "INT")
    private Integer diemThiLai;

    @Column(name = "diem_trung_binh", columnDefinition = "INT")
    private Integer diemTrungBinh;

    @Column(name = "diem_thang_bon", columnDefinition = "CHAR(2)")
    private String diemThangBon;

    //progress when submit
    @Column(name = "progress_submitted", columnDefinition = "INT")
    private Integer progressSubmitted;

    //progress when submit
    @Column(name = "is_pad", columnDefinition = "INT")
    private Integer isPaid;

    @Column(name="reject_reason")
    private String rejectReason;

    @Column(name="reject_date")
    private LocalDateTime rejectDate;
}
