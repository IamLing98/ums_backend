package com.linkdoan.backend.repository;
import com.linkdoan.backend.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

@Repository("studentRepository")
public interface StudentRepository extends JpaRepository<Student, String> {

     Student findByStudentId(String studentId);

     @Query( value = "SELECT student.*, class.course_number FROM Student,Class  WHERE (:student_id is null or :student_id =''  or student.student_id = :student_id)"
            + "and (:full_name is null or :full_name ='' or student.full_name like %:full_name%)"
            + "and (:class_id is null or :class_id ='' or student.class_id = :class_id)"
            + "and (student.class_id = class.class_id)"
            + "and (:course_number is null or :course_number = 0  or class.course_number = :course_number)"
            + "and (:department_id is null or :department_id = '' or  student.department_id = :department_id) ORDER BY student_id ASC ",
            countQuery = "SELECT count(*) FROM STUDENT,Class WHERE (:student_id is null or :student_id =''  or student.student_id = :student_id) and (:full_name is null or :full_name ='' or student.full_name like %:full_name%) and (:class_id is null or :class_id ='' or student.class_id = :class_id) and (student.class_id = class.class_id) and (:course_number is null or :course_number = 0  or class.course_number = :course_number) and (:department_id is null or :department_id = '' or  student.department_id = :department_id)",// paging SELECT SQL_CALC_FOUND_ROWS * FROM tbl limit 0, 20(with start  = page * page size, end = startt + pagesize)
            nativeQuery = true
            )
    Page<Student> findBy(@Param("student_id") String studentId,@Param("full_name") String fullName,@Param("department_id") String departmentId,@Param("class_id") String classId ,@Param("course_number") Integer courseNumber, Pageable pageable);
}
