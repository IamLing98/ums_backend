package com.linkdoan.backend.gaScheduleModel;

import java.util.ArrayList;

/**
 * @author KING
 */
public class CourseClass {


    private String id;
    private Professor professor;
    private Course course;
    private ArrayList<StudentsGroup> groups;
    private String subjectClassId;
    private int numberOfSeats = 0;
    private boolean requiresLab; //co doi hoi Lab khong?
    private int duration;        //thoi gian hoc

    public CourseClass(String id, Professor professor, Course course, ArrayList<StudentsGroup> groups, boolean requiresLab, int duration, String subjectClassId) {
        this.id = id;
        this.professor = professor;
        this.course = course;
        this.groups = groups;
        this.requiresLab = requiresLab;
        this.duration = duration;
        this.subjectClassId = subjectClassId;

        //Gan CourseClass nay cho professor
        professor.addCourseClass(this);

        //Gan CourseClass nay cho cac nhom sinh vien tham gia va numberOfSeats = tong numberOfStudents
        for (int i = 0; i < groups.size(); i++) {
            StudentsGroup studentsGroup = groups.get(i);
            studentsGroup.addCourseClass(this);
            numberOfSeats += studentsGroup.getNumberOfStudents();
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public Professor getProfessor() {
        return professor;
    }

    public Course getCourse() {
        return course;
    }

    public ArrayList<StudentsGroup> getGroups() {
        return groups;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public boolean isLabRequired() {
        return requiresLab;
    }

    public int getDuration() {
        return duration;
    }

    public String getSubjectClassId() {
        return this.subjectClassId;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public void setGroups(ArrayList<StudentsGroup> groups) {
        this.groups = groups;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public void setRequiresLab(boolean requiresLab) {
        this.requiresLab = requiresLab;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setSubjectClassId(String subjectClassId) {
        this.subjectClassId = subjectClassId;
    }


    //Returns TRUE neu lop nay trung it nhat 1 nhom SV voi lop c
    public boolean groupsOverlaps(CourseClass c) {
        for (int i = 0; i < groups.size(); i++)
            for (int j = 0; j < c.groups.size(); j++)
                if (groups.get(i) == c.getGroups().get(j))
                    return true;
        return false;
    }

    //Return TRUE neu lop nay trung giao vien p voi lop khac it nhat 1 tiet nao do
    public boolean professorOverlaps(CourseClass c) {
        return professor == c.getProfessor();
    }


}