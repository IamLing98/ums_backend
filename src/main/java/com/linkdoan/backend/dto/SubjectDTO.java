package com.linkdoan.backend.dto;

import com.linkdoan.backend.base.dto.SystemDTO;
import com.linkdoan.backend.model.Subject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubjectDTO extends SystemDTO  {

    private String subjectId;

    private String subjectName;

    private String eachSubject;

    private String theoryNumber;

    private String exerciseNumber;

    private String discussNumber;

    private String selfLearningNumber;

    private String practiceNumber;

    private String subjectForLevel;

    private String subjectType;

    private List<SubjectDTO> prerequisitesSubjects ;

    public Subject toModel(){
        Subject subject = new Subject();
        subject.setSubjectId(this.subjectId);
        subject.setSubjectName(this.subjectName);
        subject.setDiscussNumber(NumberUtils.toInt(this.discussNumber));
        subject.setEachSubject(NumberUtils.toInt(this.eachSubject));
        subject.setExerciseNumber(NumberUtils.toInt(this.exerciseNumber));
        subject.setTheoryNumber(NumberUtils.toInt(this.theoryNumber));
        subject.setPracticeNumber(NumberUtils.toInt(this.practiceNumber));
        subject.setSubjectForLevel(NumberUtils.toInt(subjectForLevel));
        subject.setSelfLearningNumber(NumberUtils.toInt(this.selfLearningNumber));
        return subject;
    }
}
