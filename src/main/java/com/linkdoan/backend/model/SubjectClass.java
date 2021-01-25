package com.linkdoan.backend.model;

import com.linkdoan.backend.dto.SubjectClassDTO;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
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

    @Column(name="group_id", columnDefinition = "INT")
    private Integer groupId;

    @Column(name="duration", columnDefinition = "INT")
    private Integer duration;

    @Column(name="type", columnDefinition = "INT")
    private Integer type;

    @Column(name="main_subject_classId", columnDefinition = "char(14)")
    private String maiSubjectClassId;

    public SubjectClassDTO toDTO(){
        SubjectClassDTO sj = new SubjectClassDTO();
        sj.setSubjectClassId(this.subjectClassId);
        sj.setTermId(this.termId);
        sj.setTeacherId(this.teacherId);
        sj.setNumberOfSeats(this.numberOfSeats);
        sj.setIsRequireLab(this.isRequireLab);
        sj.setCreatedDate(this.createdDate);
        sj.setGroupId(this.groupId);
        sj.setDuration(this.duration);
        return sj;
    }

}
