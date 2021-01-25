package com.linkdoan.backend.repository;

import com.linkdoan.backend.dto.SubjectClassDTO;
import com.linkdoan.backend.model.SubjectClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectClassRepository extends JpaRepository<SubjectClass, String> {

    @Query(value = "SELECT subjectClass.subjectClassId, subject.subjectId, subject.subjectName, subjectClass.numberOfSeats, subjectClass.isRequireLab, " +
            " employee.employeeId, employee.fullName, subject.eachSubject " +
            "FROM Subject subject inner join SubjectClass  subjectClass ON subject.subjectId = subjectClass.subjectId " +
            "left join Employee employee on subjectClass.teacherId = employee.employeeId  " +
            "WHERE subjectClass.termId = :termId "
    )
    List<Object[]> getAllByTermIdd(@Param("termId") String termId);

    @Query(value = "SELECT distinct new com.linkdoan.backend.dto.SubjectClassDTO(subjectClass.subjectClassId, subjectClass.subjectId, " +
            "subjectClass.termId, subjectClass.teacherId, subjectClass.numberOfSeats, subjectClass.isRequireLab, " +
            "subjectClass.createdDate, subjectClass.duration, subjectClass.groupId) " +
            "FROM Subject subject INNER JOIN SubjectClass subjectClass ON subject.subjectId = subjectClass.subjectId " +
            "WHERE subjectClass.termId = :termId "
    )
    List<SubjectClassDTO> getListSubjectClassByTermId(@Param("termId") String termId);

}
