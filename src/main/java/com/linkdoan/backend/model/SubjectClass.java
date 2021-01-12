package com.linkdoan.backend.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Table(name="subject_class")
@Entity
@Data
public class SubjectClass {
    @Id
    @Column(name="subject_class_id", columnDefinition = "CHAR(14)")
    private String subjectClassId;

    @Column(name="subject_id")
    private String subjectId;

    @Column(name="termId")
    private String termId;

    @Column(name="teacher_id")
    private String teacherId;

    @Column(name="number_of_seats")
    private Integer numberOfSeats;

    @Column(name="is_require_lab", columnDefinition = "INT")
    private Integer isRequireLab;

    @Column(name="created_date", columnDefinition = "DATE")
    private LocalDate createdDate;
}
