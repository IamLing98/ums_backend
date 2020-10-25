package com.linkdoan.backend.repository;

import com.linkdoan.backend.dto.YearClassDTO;
import com.linkdoan.backend.model.YearClass;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("yearClassRepository")
//@Transactional(rollbackFor = Exception.class)
public interface YearClassRepository extends JpaRepository<YearClass, String> {

    Optional<YearClass> findFirstByClassId(String classId);

    @Query(value = "SELECT distinct  new com.linkdoan.backend.dto.YearClassDTO(yearClass.classId, yearClass.className," +
            " yearClass.totalMember, yearClass.startYear, yearClass.endYear, yearClass.courseNumber, yearClass.adviserId, yearClass.nextVal," +
            " yearClass.branchId, branch.branchName, department.departmentId, department.departmentName, yearClass.educationProgramLevel," +
            " yearClass.educationProgramType, employee.employeeId, employee.fullName) " +
            "FROM YearClass  yearClass left join Branch branch on yearClass.branchId = branch.branchId " +
            "left join Department department on branch.departmentId = department.departmentId " +
            "left join Employee employee on  yearClass.teacherId = employee.employeeId  " +
            "WHERE ( :classId is null or :classId = '' or yearClass.classId = :classId) " +
            "and (:departmentId is null or :departmentId = '' or  department.departmentId = :departmentId) " +
            "and (:startYear is null or :startYear = 0 or yearClass.startYear = :startYear)"
            ,
            countQuery = "SELECT count(yearClass) FROM YearClass  yearClass left join Branch branch on yearClass.branchId = branch.branchId " +
                    "left join Department department on branch.departmentId = department.departmentId " +
                    "left join Employee employee on  yearClass.teacherId = employee.employeeId  " +
                    "WHERE ( :classId is null or :classId = '' or yearClass.classId = :classId) " +
                    "            and (:departmentId is null or :departmentId = '' or  department.departmentId = :departmentId) " +
                    "            and (:startYear is null or :startYear = 0 or yearClass.startYear = :startYear)"

    )
    Page<YearClass> findBy(@Param("classId") String classId, @Param("departmentId") String departmentId, @Param("startYear") Integer startYear, Pageable pageable);

    @Query(value = "SELECT next_val FROM Class WHERE (:class_id is null or :class_id ='' or class_id = :class_id)",
            nativeQuery = true
    )
    Optional<Integer> findNextValueSeqInClass(@Param("class_id") String classId);

    @Query(value = "SELECT distinct new com.linkdoan.backend.dto.YearClassDTO(yearClass.classId, yearClass.className, yearClass.totalMember," +
            " yearClass.startYear, yearClass.endYear, yearClass.courseNumber, yearClass.adviserId, yearClass.nextVal, branch.branchId," +
            " branch.branchName, branch.departmentId,  department.departmentName,yearClass.educationProgramLevel, " +
            "yearClass.educationProgramType, employee.employeeId, employee.fullName) " +
            "FROM YearClass  yearClass left join Branch branch on yearClass.branchId = branch.branchId " +
            "left join Department department on branch.departmentId = department.departmentId  " +
            "left join Employee employee on  yearClass.teacherId = employee.employeeId  " +
            "WHERE (yearClass.classId = :classId) "
            ,
            countQuery = "SELECT count(yearClass) FROM YearClass  yearClass left join Branch branch on yearClass.branchId = branch.branchId " +
                    "left join Department department on branch.departmentId = department.departmentId " +
                    "left join Employee employee on  yearClass.teacherId = employee.employeeId  " +
                    "WHERE (yearClass.classId = :classId) "
    )
    Page<YearClassDTO> getDetails(@Param("classId") String classId, Pageable pageable);
}
