package com.linkdoan.backend.model.primaryKey;

import lombok.Data;

import javax.persistence.*;

@Table(name="department_course_seq")
@Data
@Entity
public class DepartmentCourseNextVal {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "department_course__class_seq")
    @SequenceGenerator(name = "id_Sequence", sequenceName = "ORACLE_DB_SEQ_ID")
    private Long id;

    @Column(name = "department_id",   columnDefinition = "CHAR(7)")
    private String departmentId;

    @Column(name = "course_number", columnDefinition = "Int")
    private Integer courseNumber;

    @Column(name="next_class_value")
    private Integer nextClassValue;

    @Column(name="next_student_value")
    private Integer nextStudentValue;
}
