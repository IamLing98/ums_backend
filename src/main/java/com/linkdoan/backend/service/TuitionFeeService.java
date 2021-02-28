package com.linkdoan.backend.service;

import java.util.List;
import java.util.Map;

public interface TuitionFeeService {

    Map<String, Object> getFeeInfoOfStudent(String studentId, String termId);

    List<?> debug();
}
