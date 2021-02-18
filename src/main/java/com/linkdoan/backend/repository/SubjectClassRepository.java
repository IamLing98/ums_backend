package com.linkdoan.backend.repository;

import com.linkdoan.backend.dto.SubjectClassDTO;
import com.linkdoan.backend.model.SubjectClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectClassRepository extends JpaRepository<SubjectClass, String> {

    @Query(value =
            "SELECT distinct new com.linkdoan.backend.dto.SubjectClassDTO(subjectClass.subjectClassId, subjectClass.subjectId, " +
                    "subjectClass.termId, subjectClass.teacherId, subjectClass.numberOfSeats, subjectClass.isRequireLab, " +
                    "subjectClass.createdDate, subjectClass.duration, subjectClass.groupId) " +
                    "FROM Subject subject INNER JOIN SubjectClass subjectClass ON subject.subjectId = subjectClass.subjectId " +
                    "WHERE subjectClass.termId = :termId "
    )
    List<SubjectClassDTO> getListSubjectClassByTermId(@Param("termId") String termId);

    //getDetail subjectClass
    @Query(value =
            "SELECT scr.studentId, student.fullName, scr.diemBaiTap, scr.diemChuyenCan, scr.diemKiemTra, scr.diemThi, " +
                    "scr.diemThiLai, scr.diemTrungBinh, scr.diemThangBon  " +
                    "FROM SubjectClassRegistration scr INNER JOIN Student student ON scr.studentId = student.studentId " +
                    "WHERE scr.subjectClassId = :subjectClassId "
    )
    List<Object[]> getSubjectClassDetails(@Param("subjectClassId") String subjectClassId);

    //getObjectArraylist SubjectClass
    @Query(value =
            "SELECT subject.subjectId, subject.subjectName, subject.eachSubject, subject.departmentId, subject.theoryNumber, subject.selfLearningNumber, " +
                    "subject.exerciseNumber, subject.discussNumber, subject.practiceNumber, subjectClass.subjectClassId, subjectClass.isRequireLab, " +
                    "subjectClass.teacherId, subjectClass.duration, subjectClass.numberOfSeats, subjectClass.mainSubjectClassId, subjectClass.dayOfWeek, " +
                    "subjectClass.hourOfDay, subjectClass.roomId, employee.fullName,   department.departmentName, subject.subjectType, subjectClass.status, " +
                    "subjectClass.currentOfSubmittingNumber " +
                    "FROM SubjectClass subjectClass INNER JOIN Subject subject ON subject.subjectId = subjectClass.subjectId " +
                    "INNER JOIN Department department ON subject.departmentId = department.departmentId " +
                    "LEFT JOIN Room room ON subjectClass.roomId = room.roomId " +
                    "LEFT JOIN Employee employee ON subjectClass.teacherId = employee.employeeId " +
                    "WHERE subjectClass.termId = :termId"
    )
    List<Object[]> getSubjectClassObjectArraylist(@Param("termId") String termId);

    //find all subject class has subject id and term id
    List<SubjectClass> findAllByTermIdAndSubjectId(String termId, String subjectId);

    //update time and room for subjectClass
    @Modifying
    @Query(value =
            "UPDATE SubjectClass sc SET sc.roomId = :roomId, sc.dayOfWeek = :dayOfWeek, sc.hourOfDay = :hourOfDay WHERE sc.subjectClassId = :subjectClassId"
    )
    int updateTimeTable(@Param("subjectClassId") String subjectClassId, @Param("roomId") String roomId, @Param("dayOfWeek") Integer dayOfWeek, @Param("hourOfDay") Integer hourOfDay);

}
