package com.linkdoan.backend.repository;

import com.linkdoan.backend.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findAllByTermId(String termId);

    @Query(value = "SELECT subject.subjectId, subject.subjectName, subject.eachSubject, subject.departmentId, " +
            "subject.theoryNumber, subject.selfLearningNumber, subject.exerciseNumber, subject.discussNumber, subject.practiceNumber, " +
            "subjectClass.subjectClassId, subjectClass.isRequireLab, subjectClass.teacherId, subjectClass.duration, subjectClass.numberOfSeats, " +
            "subjectClass.mainSubjectClassId, ssc.dayOfWeek, ssc.hourOfDay, ssc.roomId, " +
            "employee.fullName " +
            "FROM Subject subject INNER JOIN SubjectClass subjectClass ON subject.subjectId = subjectClass.subjectId " +
            "INNER JOIN ScheduleSubjectClass  ssc ON subjectClass.subjectClassId = ssc.subjectClassId " +
            "INNER JOIN Schedule schedule ON ssc.scheduleId = schedule.id " +
            "INNER JOIN Room room ON ssc.roomId = room.roomId " +
            "INNER JOIN Employee employee ON subjectClass.teacherId = employee.employeeId " +
            "WHERE schedule.id = :scheduleId")
    List<Object[]> getScheduleListObject(@Param("scheduleId") Long id);
}
