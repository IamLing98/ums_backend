package com.linkdoan.backend.service;

import java.util.List;
import java.util.Map;

public interface ScheduleService {

    List<com.linkdoan.backend.model.Schedule> getSchedule(String termId);

    String initData(String termId) throws Exception;
}
