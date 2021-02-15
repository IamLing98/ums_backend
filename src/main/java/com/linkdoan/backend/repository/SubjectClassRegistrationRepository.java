package com.linkdoan.backend.repository;

import com.linkdoan.backend.model.SubjectClassRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubjectClassRegistrationRepository extends JpaRepository<SubjectClassRegistration, Long> {
    //get list submitted of student
    @Query(value =
            "SELECT scr.id, scr.autoSubmit, scr.studentId, scr.subjectClassId, scr.submittedDate, scr.termId, scr.status, scr.progressSubmitted, " +
                    "s.subjectName, s.eachSubject, s.subjectId, sc.roomId, sc.duration, sc.dayOfWeek, sc.hourOfDay " +
                    "FROM SubjectClassRegistration scr INNER JOIN SubjectClass sc ON scr.subjectClassId = sc.subjectClassId " +
                    "INNER JOIN Subject s ON sc.subjectId = s.subjectId "
    )
    List<Object[]> getListSubmittedByStudentIdAndTermId(@Param("studentId") String studentId, @Param("termId") String termId);


    //check exist

    Optional<SubjectClassRegistration> findFirstBySubjectClassIdAndStudentIdAndTermId(String subjectClassId, String studentId, String termId);
}
