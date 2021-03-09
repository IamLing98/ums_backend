package com.linkdoan.backend.dto;

import com.linkdoan.backend.base.dto.FileDTO;
import com.linkdoan.backend.model.Subject;
import com.linkdoan.backend.model.SubjectClass;
import com.linkdoan.backend.model.Term;
import lombok.Data;

import javax.persistence.Column;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Data
public class SubjectClassDTO extends FileDTO {

    private String subjectClassId;

    private String subjectId;

    private Subject subject;

    private String termId;

    private Term term;

    private String teacherId;

    private String teacherName;

    private Integer numberOfSeats;

    private Integer isRequireLab;

    private LocalDate createdDate;

    private Integer groupId;

    private Integer duration;

    private Integer type;

    private String mainSubjectClassId;

    private String roomId;

    private Integer dayOfWeek;

    private Integer hourOfDay;

    private Integer currentOfSubmittingNumber = 0;

    private Integer status = 0;

    private Integer currentWeek = 0;

    private Integer hasGrade = 0;

    private LocalDateTime gradeImportTime;

    private List<Map<String, Object>> studentList;

    public SubjectClassDTO(String subjectClassId, String subjectId, String termId, String teacherId,
                           Integer numberOfSeats, Integer isRequireLab, LocalDate createdDate, Integer groupId,
                           Integer duration, Integer type, String mainSubjectClassId, String roomId, Integer dayOfWeek,
                           Integer hourOfDay, Integer currentOfSubmittingNumber, Integer status, Integer currentWeek,
                           String teacherName, Term term, Integer hasGrade, LocalDateTime gradeImportTime) {
        this.subjectClassId = subjectClassId;
        this.subjectId = subjectId;
        this.termId = termId;
        this.teacherId = teacherId;
        this.numberOfSeats = numberOfSeats;
        this.isRequireLab = isRequireLab;
        this.createdDate = createdDate;
        this.groupId = groupId;
        this.duration = duration;
        this.type = type;
        this.mainSubjectClassId = mainSubjectClassId;
        this.roomId = roomId;
        this.dayOfWeek = dayOfWeek;
        this.hourOfDay = hourOfDay;
        this.currentOfSubmittingNumber = currentOfSubmittingNumber;
        this.status = status;
        this.currentWeek = currentWeek;
        this.teacherName = teacherName;
        this.term = term;
        this.hasGrade = hasGrade;
        this.gradeImportTime = gradeImportTime;
    }

    public SubjectClass toModel() {
        SubjectClass sj = new SubjectClass();
        sj.setSubjectClassId(this.subjectClassId);
        sj.setTermId(this.termId);
        sj.setTeacherId(this.teacherId);
        sj.setNumberOfSeats(this.numberOfSeats);
        sj.setIsRequireLab(this.isRequireLab);
        sj.setCreatedDate(this.createdDate);
        sj.setSubjectId(this.subjectId);
        sj.setMainSubjectClassId(this.mainSubjectClassId);
        sj.setDuration(this.duration);
        sj.setType(this.type);
        sj.setGroupId(this.groupId);
        return sj;
    }

    public SubjectClassDTO() {
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
