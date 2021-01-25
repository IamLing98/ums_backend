package com.linkdoan.backend.dto;

import com.linkdoan.backend.model.SubjectClass;
import lombok.Data;

import java.time.LocalDate;

@Data
public class SubjectClassDTO {
    private String subjectClassId;

    private String subjectId;

    private String termId;

    private String teacherId;

    private Integer numberOfSeats;

    private Integer isRequireLab;

    private LocalDate createdDate;

    private Integer duration;

    private Integer groupId;

    public SubjectClass toModel(){
        SubjectClass sj = new SubjectClass();
        sj.setSubjectClassId(this.subjectClassId);
        sj.setTermId(this.termId);
        sj.setTeacherId(this.teacherId);
        sj.setNumberOfSeats(this.numberOfSeats);
        sj.setIsRequireLab(this.isRequireLab);
        sj.setCreatedDate(this.createdDate);
        sj.setSubjectId(this.subjectId);
        return sj;
    }

    public SubjectClassDTO( ) {
    }

    public SubjectClassDTO(String subjectClassId, String subjectId, String termId, String teacherId, Integer numberOfSeats,
                           Integer isRequireLab, LocalDate createdDate, Integer duration, Integer groupId) {
        this.subjectClassId = subjectClassId;
        this.subjectId = subjectId;
        this.termId = termId;
        this.teacherId = teacherId;
        this.numberOfSeats = numberOfSeats;
        this.isRequireLab = isRequireLab;
        this.createdDate = createdDate;
        this.duration = duration;
        this.groupId = groupId;
    }
}
