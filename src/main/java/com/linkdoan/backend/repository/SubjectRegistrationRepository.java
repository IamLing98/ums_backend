package com.linkdoan.backend.repository;

import com.linkdoan.backend.dto.SubjectRegistrationDTO;
import com.linkdoan.backend.model.SubjectRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SubjectRegistrationRepository extends JpaRepository<SubjectRegistration, Integer> {

    @Query(value = "SELECT distinct new com.linkdoan.backend.dto.SubjectRegistrationDTO( ) " +
            "FROM Subject  subject INNER JOIN YearClassSubject yearClassSubject on subject.subjectId = yearClassSubject.subjectId " +
            "left join Branch branch on yearClass.branchId = branch.branchId " +
            "left join Department  department on branch.departmentId = department.departmentId " +
            "left join Nationality  nationality on student.nationalityId = nationality.nationalityId " +
            "left join Ethnic  ethnic on student.ethnicId = ethnic.ethnicId " +
            "WHERE student.studentId = :studentId "
    )
    List<SubjectRegistrationDTO> getListSubjectRegistrationByStudentIdAndTermId(@Param("studentId") String studentId, @Param("termId") String termId);
}
