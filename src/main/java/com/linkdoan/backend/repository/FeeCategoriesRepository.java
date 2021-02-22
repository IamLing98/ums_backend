package com.linkdoan.backend.repository;

import com.linkdoan.backend.dto.StudentDTO;
import com.linkdoan.backend.model.FeeCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FeeCategoriesRepository extends JpaRepository<FeeCategory, Long> {

    //get list student
    @Query(value =
            "SELECT distinct new com.linkdoan.backend.dto.StudentDTO(student.studentId, student.fullName,  yearClass) " +
                    "FROM Student student inner join YearClass  yearClass on student.yearClassId = yearClass.classId " +
                    "INNER JOIN Department department ON yearClass.departmentId = department.departmentId " +
                    "WHERE student.learningStatus = 1 "
    )
    List<StudentDTO> getAllStudent();


    List<FeeCategory> findAllByFeeCategoryGroupId(Long feeCategoryGroupId);

}
