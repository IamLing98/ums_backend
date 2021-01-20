package com.linkdoan.backend.dto;

import com.linkdoan.backend.base.dto.SystemDTO;
import com.linkdoan.backend.model.PrerequisitesSubject;
import com.linkdoan.backend.model.Subject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubjectDTO extends SystemDTO  {

    private String subjectId;

    private String subjectName;

    private Integer eachSubject;

    private Integer theoryNumber;

    private Integer exerciseNumber;

    private Integer discussNumber;

    private Integer selfLearningNumber;

    private Integer practiceNumber;

    private Integer subjectForLevel;

    private String departmentId;

    private List<String> preLearnSubjectList ;

    public SubjectDTO(String subjectId, String subjectName) {
        this.subjectId = subjectId;
        this.subjectName = subjectName;
    }

    public SubjectDTO(String subjectId, String subjectName, Integer eachSubject, Integer theoryNumber, Integer exerciseNumber, Integer discussNumber, Integer selfLearningNumber, Integer practiceNumber, Integer subjectForLevel) {
        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.eachSubject = eachSubject;
        this.theoryNumber = theoryNumber;
        this.exerciseNumber = exerciseNumber;
        this.discussNumber = discussNumber;
        this.selfLearningNumber = selfLearningNumber;
        this.practiceNumber = practiceNumber;
        this.subjectForLevel = subjectForLevel;
    }

    public SubjectDTO(String subjectId, String subjectName, Integer eachSubject, Integer theoryNumber, Integer exerciseNumber, Integer discussNumber, Integer selfLearningNumber, Integer practiceNumber, Integer subjectForLevel, String departmentId) {
        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.eachSubject = eachSubject;
        this.theoryNumber = theoryNumber;
        this.exerciseNumber = exerciseNumber;
        this.discussNumber = discussNumber;
        this.selfLearningNumber = selfLearningNumber;
        this.practiceNumber = practiceNumber;
        this.subjectForLevel = subjectForLevel;
        this.departmentId = departmentId;
    }


    public Subject toModel(){
        Subject subject = new Subject();
        subject.setSubjectId(this.subjectId);
        subject.setSubjectName(this.subjectName);
        subject.setEachSubject(this.eachSubject);
        subject.setTheoryNumber(this.theoryNumber);
        subject.setExerciseNumber(this.exerciseNumber);
        subject.setDiscussNumber(this.discussNumber);
        subject.setSelfLearningNumber(this.selfLearningNumber);
        subject.setPracticeNumber(this.practiceNumber);
        subject.setSubjectForLevel(this.subjectForLevel);
        subject.setDepartmentId(this.departmentId);
        return subject;
    }

    public List<PrerequisitesSubject> toPrerequisitesSubjectList(){
        List<PrerequisitesSubject> prerequisitesSubjectList = new ArrayList<>();
        for(String  id : this.preLearnSubjectList){
            PrerequisitesSubject prerequisitesSubject = new PrerequisitesSubject();
            prerequisitesSubject.setSubjectId(this.subjectId);
            prerequisitesSubject.setPrerequisitesSubjectId(id);
            prerequisitesSubjectList.add(prerequisitesSubject);
        }
        return prerequisitesSubjectList;
    }
}
