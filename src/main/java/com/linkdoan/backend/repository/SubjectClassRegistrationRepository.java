package com.linkdoan.backend.repository;

import com.linkdoan.backend.model.Subject;
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
                    "s.subjectName, s.eachSubject, s.subjectId, sc.roomId, sc.duration, sc.dayOfWeek, sc.hourOfDay, yc.classId, yc.currentTerm," +
                    "sc.teacherId, employee.fullName  " +
                    "FROM SubjectClassRegistration scr " +
                    "INNER JOIN SubjectClass sc ON scr.subjectClassId = sc.subjectClassId " +
                    "INNER JOIN Subject s ON sc.subjectId = s.subjectId " +
                    "INNER JOIN Student student ON scr.studentId = student.studentId " +
                    "INNER JOIN YearClass yc ON student.yearClassId = yc.classId " +
                    "LEFT JOIN Employee employee ON sc.teacherId = employee.employeeId " +
                    "WHERE scr.studentId = :studentId and scr.termId = :termId " +
                    "AND scr.termId = :termId " +
                    "AND (:status IS NULL OR scr.status = :status) "
    )
    List<Object[]> getListSubmittedByStudentIdAndTermId(@Param("studentId") String studentId, @Param("termId") String termId, @Param("status") Integer status);

    //get list submitted subject of student
    @Query(value =
            "SELECT DISTINCT(sj) " +
                    "FROM SubjectClassRegistration scr INNER JOIN SubjectClass sc ON scr.subjectClassId = sc.subjectClassId " +
                    "INNER JOIN Subject sj ON sc.subjectId = sj.subjectId " +
                    "WHERE scr.studentId = :studentId AND scr.termId = :termId and scr.status = 1"
    )
    List<Subject> getListSubmittedSubjectOfStudentInTerm(@Param("studentId") String studentId, @Param("termId") String termId);

    //get list submitted by subjectClassId and TermId
    List<SubjectClassRegistration> findAllBySubjectClassIdAndTermId(String subjectClassId, String termid);

    //check exist

    Optional<SubjectClassRegistration> findFirstBySubjectClassIdAndStudentIdAndTermId(String subjectClassId, String studentId, String termId);

    List<SubjectClassRegistration> findAllByStudentIdAndTermIdAndStatus(String studentId, String termId, Integer status);

    List<SubjectClassRegistration> findAllByTermIdAndStatus(String termId, Integer status);
}
