package com.linkdoan.backend.repository;

import com.linkdoan.backend.model.SubjectClassRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectClassRegistrationRepository extends JpaRepository<SubjectClassRegistration, Long> {
    //get list submitted of student
    @Query(value =
            "SELECT  scr.subjectClassId, scr.subjectId, sc.roomId, sc.hourOfDay, sc.dayOfWeek, sc.duration, sj.subjectName, sj.eachSubject," +
                    "scr.progressSubmitted, scr.status " +
                    "FROM SubjectClassRegistration scr INNER JOIN SubjectClass sc ON scr.subjectClassId = sc.subjectClassId " +
                    "INNER JOIN Subject sj ON sc.subjectId = sj.subjectId " +
                    "WHERE scr.studentId = :studentId and sc.termId = :termId"
    )
    List<Object[]> getListSubmittedByStudentIdAndTermId(@Param("studentId") String studentId, @Param("termId") String termId);

//
//    //when delete schedule, I find all submitting deal with schedule and DELETE
//    @Modifying
//    @Query(value =
//            "DELETE  FROM SubjectClassRegistration scr " +
//                    "WHERE scr.scheduleSubjectClassId " +
//                    "IN ( SELECT ssc.id FROM ScheduleSubjectClass ssc WHERE ssc.scheduleId = :scheduleId ) "
//    )
//    int deleteAllSubmittingDealWithSchedule(@Param("scheduleId") Long scheduleId);
}
