package com.linkdoan.backend.repository;

import com.linkdoan.backend.model.TeacherEducationTimeLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TeacherEducationTimelineRepository extends JpaRepository<TeacherEducationTimeLine, Long> {


    @Modifying
    @Query(
            value = "DELETE FROM TeacherEducationTimeLine TET " +
                    "WHERE TET.teacherId = :employeeId"
    )
    int deleteAllByTeacherId(@Param("employeeId") String employeeId);
}
