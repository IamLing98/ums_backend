package com.linkdoan.backend.repository;

import com.linkdoan.backend.dto.StudentSubjectDTO;
import com.linkdoan.backend.model.StudentSubject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentSubjectRepository extends JpaRepository<StudentSubject, Long> {

    Optional<StudentSubject> findFirstByStudentIdAndEducationProgramIdAndSubjectId(String studentId, String educationProgramId, String subjectId);

    @Query(
            value = "SELECT new com.linkdoan.backend.dto.StudentSubjectDTO(SS.id, SS.studentId, SS.educationProgramId, SS.subjectId, S.subjectName," +
                    "S.eachSubject, SS.diemTrungBinh, SS.diemThangBon, SS.diemChu)" +
                    "FROM StudentSubject SS INNER JOIN Subject  S ON SS.subjectId = S.subjectId " +
                    "WHERE SS.studentId = :studentId AND SS.educationProgramId = :educationProgramId"
    )
    List<StudentSubjectDTO> findAllByStudentIdAndEducationProgramId(@Param("studentId") String studentId, @Param("educationProgramId") String educationProgramId);

}
