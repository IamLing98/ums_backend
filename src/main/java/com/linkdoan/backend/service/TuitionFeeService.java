package com.linkdoan.backend.service;

import com.linkdoan.backend.model.Subject;

import java.util.List;
import java.util.Map;

public interface TuitionFeeService {

    List<Map<String, Object>> getStudentList();

    Map<String, Object> getFeeInfoOfStudent(String studentId, String termId);

    List<?> debug();
}
