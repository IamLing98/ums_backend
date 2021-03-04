package com.linkdoan.backend.repository;

import com.linkdoan.backend.model.TeacherWorkTimeLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TeacherWorkTimelineRepository extends JpaRepository<TeacherWorkTimeLine, Long> {

    @Modifying
    @Query(
            value = "DELETE FROM TeacherWorkTimeLine TWT " +
                    "WHERE TWT.teacherId = :employeeId"
    )
    int deleteAllByTeacherId(@Param("employeeId") String employeeId);
}
