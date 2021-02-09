package com.linkdoan.backend.gaScheduleModel;

import java.util.ArrayList;

/**
 * @author KING
 */
public class Professor {
    private String id;
    private String name;
    private final ArrayList<CourseClass> courseClasses;

    public Professor() {
        name = new String();
        courseClasses = new ArrayList();
    }

    public Professor(String id, String name) {
        this.id = id;
        this.name = name;
        courseClasses = new ArrayList();
    }


    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }


    public ArrayList<CourseClass> getCourseClasses() {
        return courseClasses;
    }

    public void addCourseClass(CourseClass courseClass) {
        courseClasses.add(courseClass);
    }

}
