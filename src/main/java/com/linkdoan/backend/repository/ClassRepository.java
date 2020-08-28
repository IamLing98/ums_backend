package com.linkdoan.backend.repository;

import com.linkdoan.backend.model.Class;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(rollbackFor = Exception.class)
public interface ClassRepository extends JpaRepository<Class, String> {

    Optional<Class> findFirstByClassId(String classId);

    @Query(value = "SELECT * FROM class c   WHERE (:class_id is null or :class_id =''  or class_id = :class_id)"
            + "and (:department_id is null or :department_id =\"\" or c.department_id =  :department_id)"
            + "and (:course_number is null or :course_number =\"\" or course_number =  :course_number)"
            + "ORDER BY class_id ASC ",
            countQuery = "SELECT count(*) FROM Class WHERE (:class_id is null or :class_id =''  or class_id = :class_id)"
                    + " and (:department_id is null or :department_id ='' or department_id =:department_id)"
                    + "and (:course_number is null or :course_number =\"\" or course_number =  :course_number)",
            nativeQuery = true
    )
    Page<Class> findBy(@Param("class_id") String classId, @Param("department_id") String departmentId, @Param("course_number") String courseNumber, Pageable pageable);

    @Query(value = "SELECT next_val FROM Class WHERE (:class_id is null or :class_id ='' or class_id = :class_id)",
            nativeQuery = true
    )
    Optional<Integer> findNextValueSeqInClass(@Param("class_id") String classId);

}
