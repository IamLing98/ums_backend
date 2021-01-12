package com.linkdoan.backend.dto;

import com.linkdoan.backend.model.SubjectClass;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
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

    public SubjectClass toModel(){
        SubjectClass sj = new SubjectClass();
        sj.setSubjectClassId(this.subjectClassId);
        sj.setTermId(this.termId);
        sj.setTeacherId(this.teacherId);
        sj.setNumberOfSeats(this.numberOfSeats);
        sj.setIsRequireLab(this.isRequireLab);
        sj.setCreatedDate(this.createdDate);
        return sj;
    }
}
