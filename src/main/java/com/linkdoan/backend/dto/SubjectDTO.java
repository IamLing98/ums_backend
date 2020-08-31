package com.linkdoan.backend.dto;

import com.linkdoan.backend.base.anotation.AdjHistory;
import com.linkdoan.backend.base.dto.SystemDTO;
import com.linkdoan.backend.model.PrerequisitesSubject;
import com.linkdoan.backend.model.Subject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.math.NumberUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubjectDTO extends SystemDTO  {

    private String subjectId="";

    private String subjectName="";

    private String eachSubject="0";

    private String theoryNumber="0";

    private String exerciseNumber="0";

    private String discussNumber="0";

    private String selfLearningNumber="0";

    private String practiceNumber="0";

    private String subjectForLevel="0";

    private String subjectType="0";

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
