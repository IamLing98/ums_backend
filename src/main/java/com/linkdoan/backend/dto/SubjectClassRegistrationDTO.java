package com.linkdoan.backend.dto;

import com.linkdoan.backend.base.dto.FileDTO;
import com.linkdoan.backend.model.Subject;
import com.poiji.annotation.ExcelCell;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubjectClassRegistrationDTO extends FileDTO {

    private Long id;

    private String studentId;

    private String subjectClassId;

    private String subjectId;

    private String subjectName;

    private Integer eachSubject;

    private Subject subject;

    private SubjectClassDTO subjectClassDTO;

    private String termId;

    private LocalDateTime submittedDate;

    private Integer autoSubmit;

    private Integer status;

    private Double diemChuyenCan;

    private Double diemBaiTap;

    private Double diemKiemTra;

    private Double diemThi;

    private Double diemThiLai;

    private Double diemTrungBinh;

    private Double diemThangBon;

    private String diemChu;

    private Integer progressSubmitted;

    private Integer isPaid;

    private String rejectReason;

    private LocalDateTime rejectDate;
}
