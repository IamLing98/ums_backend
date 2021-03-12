package com.linkdoan.backend.repository;

import com.linkdoan.backend.dto.EducationProgramDTO;
import com.linkdoan.backend.model.EducationProgram;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EducationProgramRepository extends JpaRepository<EducationProgram, String> {

    List<EducationProgram> findAll();

    @Query(value =
            "SELECT   new com.linkdoan.backend.dto.EducationProgramDTO( ep.educationProgramId, ep.educationProgramName, ep.educationProgramLevel," +
                    "branch.branchId, ep.educationProgramType, ep.educationProgramStatus, branch.branchName ,ep.totalTerm, department.departmentId, department.departmentName) " +
                    "FROM EducationProgram  ep inner join Department department on ep.departmentId = department.departmentId " +
                    "inner join Branch branch on ep.branchId = branch.branchId " +
                    "WHERE (:branchId is null or :branchId = '' or ep.branchId = :branchId) and (:educationProgramId is null or :educationProgramId ='' or ep.educationProgramId = :educationProgramId) "
    )
    List<EducationProgramDTO> findAll(@Param("branchId") String branchId, @Param("educationProgramId") String educationProgramId);


    @Query(value =
            "SELECT NEW com.linkdoan.backend.dto.EducationProgramDTO( ep.educationProgramId, ep.educationProgramName, ep.educationProgramLevel," +
                    "branch.branchId, ep.educationProgramType, ep.educationProgramStatus, branch.branchName ,ep.totalTerm, department.departmentId, department.departmentName) " +
                    "FROM EducationProgram  ep INNER JOIN Department department ON ep.departmentId = department.departmentId " +
                    "INNER JOIN Branch branch ON ep.branchId = branch.branchId " +
                    "WHERE ( ep.educationProgramId = :educationProgramId) "
    )
    List<Object[]> findListSubjectObjectByEducationProgramId(@Param("educationProgramId") String educationProgramId);

    @Query(value =
            "SELECT NEW com.linkdoan.backend.dto.EducationProgramDTO( ep.educationProgramId, ep.educationProgramName, ep.educationProgramLevel," +
                    "branch.branchId, ep.educationProgramType, ep.educationProgramStatus, branch.branchName ,ep.totalTerm, " +
                    "department.departmentId, department.departmentName, ep.totalEachSubject) " +
                    "FROM EducationProgram  ep INNER JOIN Department department ON ep.departmentId = department.departmentId " +
                    "INNER JOIN Branch branch ON ep.branchId = branch.branchId " +
                    "WHERE ( ep.educationProgramId = :educationProgramId) "
    )
    EducationProgramDTO getDetail(@Param("educationProgramId") String educationProgramId);

    @Query(value =
            "SELECT subject.departmentId, subject.discussNumber, subject.eachSubject, subject.exerciseNumber, " +
                    "subject.practiceNumber, subject.selfLearningNumber, subject.subjectForLevel, subject.subjectId, subject.subjectName, " +
                    "subject.theoryNumber, department.departmentName, epd.term " +
                    "FROM Subject subject  INNER JOIN Department department ON subject.departmentId = department.departmentId " +
                    "INNER JOIN EducationProgramSubject epd ON subject.subjectId = epd.subjectId " +
                    "WHERE ( epd.educationProgramId = :educationProgramId) "
    )
    List<Object[]> getCorrectListSubjectByEp(@Param("educationProgramId") String educationProgramId);

    @Query(value =
            "SELECT subject " +
                    "FROM EducationProgram EP INNER JOIN EducationProgramSubject EPS ON EP.educationProgramId = EPS.educationProgramId " +
                    "INNER JOIN Subject subject ON EPS.subjectId = subject.subjectId " +
                    "LEFT JOIN SubjectClassRegistration "
    )
    List<Object[]> getCorrectListSubjectByEpWithResult(@Param("educationProgramId") String educationProgramId);

}
