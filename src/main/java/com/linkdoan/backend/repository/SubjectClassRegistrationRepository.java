package com.linkdoan.backend.repository;

import com.linkdoan.backend.dto.SubjectClassDTO;
import com.linkdoan.backend.model.SubjectClassRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubjectClassRegistrationRepository extends JpaRepository<SubjectClassRegistration, Long> {
    List<SubjectClassRegistration> findAllBySubjectClassIdAndTermId(String subjectClassId, String termId);

    Optional<SubjectClassRegistration> findFirstBySubjectClassIdAndStudentId(String subjectClassId, String studentId);

    List<SubjectClassRegistration> findAllByStudentIdAndSubjectIdAndTermId(String studentId, String subjectId, String termId);

    //get list submitted of student
    @Query(value =
            "SELECT NEW com.linkdoan.backend.dto.SubjectClassDTO(scr.subjectClassId, scr.subjectId, scr.termId, " +
                    "sc.teacherId, sc.numberOfSeats, sc.isRequireLab, sc.createdDate, sc.duration, sc.groupId) " +
                    "FROM SubjectClassRegistration scr INNER JOIN SubjectClass sc ON scr.subjectClassId = sc.subjectClassId " +
                    "WHERE scr.studentId = :studentId and scr.termId = :termId"
    )
    List<SubjectClassDTO> getListSubmittedByStudentIdAndTermId(@Param("studentId") String studentId, @Param("termId") String termId);
}
