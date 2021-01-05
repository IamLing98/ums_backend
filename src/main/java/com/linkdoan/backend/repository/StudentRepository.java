package com.linkdoan.backend.repository;

import com.linkdoan.backend.dto.StudentDTO;
import com.linkdoan.backend.dto.StudentDetailsDTO;
import com.linkdoan.backend.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("studentRepository")
public interface StudentRepository extends JpaRepository<Student, String> {

    @Query(value = "SELECT new com.linkdoan.backend.dto.StudentDTO(student.studentId, student.fullName, student.sex, department.departmentName, yearClass.classId, yearClass.className, branch.branchName, yearClass.courseNumber, student.status, yearClass.startYear, yearClass.endYear) " +
            "FROM  Student student left join  YearClass yearClass on student.yearClassId = yearClass.classId  " +
            "left join Branch branch on yearClass.branchId = branch.branchId " +
            "left join Department  department on branch.departmentId = department.departmentId " +
            "WHERE (:studentId is null or :studentId =''  or student.studentId = :studentId) " +
            "and (:startYear is null or :startYear  < 1994  or yearClass.startYear = :startYear) " +
            "and (:departmentId is null or :departmentId = '' or department.departmentId = :departmentId)"
            ,
            countQuery = "SELECT count(student) " +
                    "FROM  Student student left join  YearClass yearClass on student.yearClassId = yearClass.classId " +
                    "left join Branch branch on yearClass.branchId = branch.branchId " +
                    "left join Department  department on branch.departmentId = department.departmentId " +
                    "WHERE (:studentId is null or :studentId =''  or student.studentId = :studentId) " +
                    "and (:startYear is null or :startYear < 1994  or yearClass.startYear = :startYear)" +
                    "and (:departmentId is null or :departmentId = '' or department.departmentId = :departmentId)"

            // paging SELECT SQL_CALC_FOUND_ROWS * FROM tbl limit 0, 20(with start  = page * page size, end = startt + pagesize)
            //,nativeQuery = true
    )
    Page<StudentDTO> findAllBy(@Param("studentId") String studentId, @Param("startYear") Integer startYear, @Param("departmentId") String departmentId, Pageable pageable);

    @Query(value = "SELECT new com.linkdoan.backend.dto.StudentDetailsDTO(student.studentId, student.fullName, student.sex, student.dateBirth, " +
            "student.nickName, student.homeTown, nationality.nationalityId, nationality.nationalityName, student.bornPlace, student.permanentResidence," +
            " ethnic.ethnicId, ethnic.ethnicName, student.religion, student.enrollmentArea, student.priorityType,student.educationLevel, student.incentivesType, " +
            "student.familyElement, student.CYUStartDate, student.CPStartDate, student.identityNumber, student.identityCreatedDate, " +
            "student.identityCreatedPlace, student.bankNumber, student.email, student.phoneNumber, student.fatherName, student.fatherDateBirth," +
            " student.fatherWork, student.motherName, student.motherDateBirth, student.motherWork, student.contactAddress, student.note, " +
            "student.avatar, department.departmentId, department.departmentName, yearClass.classId, yearClass.className, branch.branchId," +
            " branch.branchName, yearClass.courseNumber, student.status, student.enrollId,  student.admissionType, yearClass.startYear, yearClass.endYear) " +
            "FROM  Student student left join  YearClass yearClass on student.yearClassId = yearClass.classId  " +
            "left join Branch branch on yearClass.branchId = branch.branchId " +
            "left join Department  department on branch.departmentId = department.departmentId " +
            "left join Nationality  nationality on student.nationalityId = nationality.nationalityId " +
            "left join Ethnic  ethnic on student.ethnicId = ethnic.ethnicId " +
            "WHERE student.studentId = :studentId "
    )
    StudentDetailsDTO getDetails(@Param("studentId") String studentId);

    @Query(value = "SELECT new com.linkdoan.backend.dto.StudentDTO(student.studentId, student.fullName, student.sex, department.departmentName, yearClass.classId, yearClass.className, branch.branchName, yearClass.courseNumber, student.status, yearClass.startYear, yearClass.endYear) " +
            "FROM  Student student left join  YearClass yearClass on student.yearClassId = yearClass.classId  " +
            "left join Branch branch on yearClass.branchId = branch.branchId " +
            "left join Department  department on branch.departmentId = department.departmentId " +
            "WHERE (yearClass.classId = :classId) "

            ,
            countQuery = "SELECT count(student) " +
                    "FROM  Student student left join  YearClass yearClass on student.yearClassId = yearClass.classId " +
                    "left join Branch branch on yearClass.branchId = branch.branchId " +
                    "left join Department  department on branch.departmentId = department.departmentId " +
                    "WHERE (yearClass.classId = :classId) "

            // paging SELECT SQL_CALC_FOUND_ROWS * FROM tbl limit 0, 20(with start  = page * page size, end = startt + pagesize)
            //,nativeQuery = true
    )
    Page<StudentDTO> findAllByClassId(@Param("classId") String classId , Pageable pageable);

    //find all student by current term
    @Query(value = "SELECT distinct new com.linkdoan.backend.dto.StudentDTO(student.studentId,  student.yearClassId, student.educationProgramId, yearClass.currentTerm) " +
            " FROM Student student inner join YearClass  yearClass on student.yearClassId = yearClass.classId " +
            "WHERE (yearClass.currentTerm < 3) "
    )
    List<StudentDTO> findAllStudentHasTermIsOne();

}
