package com.linkdoan.backend.repository;

import com.linkdoan.backend.dto.StudentGraduationDTO;
import com.linkdoan.backend.dto.StudentSubjectDTO;
import com.linkdoan.backend.model.EducationProgramSubject;
import com.linkdoan.backend.model.Student;
import com.linkdoan.backend.model.StudentGraduation;
import com.linkdoan.backend.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface StudentGraduationRepository extends JpaRepository<StudentGraduation, Long> {

    @Query(
            value = "SELECT new com.linkdoan.backend.dto.StudentSubjectDTO(SS.id, SS.studentId, SS.educationProgramId, SS.subjectId," +
                    " S, S.eachSubject, SS.diemChuyenCan, SS.diemBaiTap, SS.diemKiemTra, SS.diemThi,SS.diemThiLai, SS.diemTrungBinh," +
                    " SS.diemThangBon, SS.diemChu) " +
                    "FROM Subject S INNER JOIN StudentSubject SS ON S.subjectId = SS.subjectId " +
                    "WHERE SS.studentId = :studentId AND SS.educationProgramId = :educationProgramId AND SS.diemTrungBinh > 4.0 "
    )
    List<StudentSubjectDTO> findAllSubjectGoodResult(@Param("studentId") String studentId, @Param("educationProgramId") String educationProgramId);

    @Query(
            value = "SELECT EPS " +
                    "FROM EducationProgram EP INNER JOIN EducationProgramSubject EPS ON EP.educationProgramId = EPS.educationProgramId " +
                    "INNER JOIN Subject S ON EPS.subjectId = S.subjectId " +
                    "WHERE EPS.subjectId = :subjectId AND EPS.educationProgramId = :educationProgramId  "
    )
    EducationProgramSubject findEducationProgramSubjectByEducationProgramIdAndSubjectId(@Param("educationProgramId") String educationProgramId,
                                                                                        @Param("subjectId") String subjectId);

    @Query(
            value = "SELECT student " +
                    "FROM Student student WHERE student.status = 1"
    )
    List<Student> findAllPredictStudentGraduationInTerm();

    @Query(
            value = "SELECT student " +
                    "FROM Student student INNER JOIN YearClass yearClass ON student.yearClassId = yearClass.classId " +
                    "WHERE student.status = 1 AND yearClass.currentTerm > 6"
    )
    List<Student> findAllStudents();

    @Query(
            value="SELECT new com.linkdoan.backend.dto.StudentGraduationDTO(SG.id, SG.studentId, S, SG.termId, T, SG.status, " +
                    "SG.educationProgramId, EP, SG.CPA, SG.rankValue, SG.totalEachSubject) " +
                    "FROM StudentGraduation SG INNER JOIN EducationProgram EP ON SG.educationProgramId = EP.educationProgramId " +
                    "INNER JOIN Term T ON SG.termId = T.id INNER JOIN Student S ON SG.studentId = S.studentId " +
                    "WHERE SG.termId = :termId "
    )
    List<StudentGraduationDTO> findAllByTermId(@Param("termId") String termId);


}
