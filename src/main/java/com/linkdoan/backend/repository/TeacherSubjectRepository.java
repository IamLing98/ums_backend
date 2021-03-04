package com.linkdoan.backend.repository;

import com.linkdoan.backend.model.TeacherSubject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TeacherSubjectRepository extends JpaRepository<TeacherSubject, Long> {

    @Modifying
    @Query(
            value = "DELETE FROM TeacherSubject TS " +
                    "WHERE TS.teacherId = :employeeId"
    )
    int deleteAllByTeacherId(@Param("employeeId") String employeeId);
}
