package com.linkdoan.backend.service;

import com.linkdoan.backend.dto.SubjectClassDTO;

import java.util.List;

public interface ScheduleService {

    String initData(String termId) throws Exception;

    List<SubjectClassDTO> getSchedule(String teacherId, String termId);
}
