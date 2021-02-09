package com.linkdoan.backend.service;

import java.util.List;
import java.util.Map;

public interface NotificationsService {

    Map<String, Object> getAllNotifications(String userName);

    int updateNotifications(List<Long> ids, String userName);
}
