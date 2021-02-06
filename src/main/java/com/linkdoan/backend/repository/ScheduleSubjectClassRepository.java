package com.linkdoan.backend.repository;

import com.linkdoan.backend.model.ScheduleSubjectClass;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ScheduleSubjectClassRepository extends JpaRepository<ScheduleSubjectClass, Long> {

    List<ScheduleSubjectClass> findByScheduleIdAndSubjectId(Long scheduleId, String subjectId);

    Optional<ScheduleSubjectClass> findFirstByScheduleIdAndSubjectClassId(Long scheduleId, String subjectClassId);

    List<ScheduleSubjectClass> findAllByScheduleId(Long scheduleId);
}
