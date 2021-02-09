package com.linkdoan.backend.service.impl;

import com.linkdoan.backend.model.User;
import com.linkdoan.backend.repository.NotificationsRepository;
import com.linkdoan.backend.repository.UserRepository;
import com.linkdoan.backend.service.NotificationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
        (
                propagation = Propagation.REQUIRED,
                readOnly = false,
                rollbackFor = Throwable.class
        )
public class NotificationsServiceImpl implements NotificationsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    NotificationsRepository notificationsRepository;

    @Override
    public Map<String, Object> getAllNotifications(String userName) {
        User user = userRepository.findByUsername(userName);
        if (user != null) {
            List<Object[]> notificationsObjectArrayList = notificationsRepository.getAllNotificationsOfUser(user.getUserId());
            int notRead = notificationsRepository.getCountOfNotReadNoti(user.getUserId());
            String[] labels = {"senderId", "senderUsername", "seqId", "notificationId", "content", "status", "title", "createdDate"};
            Map<String, Object> notificationsObjectMap = new HashMap<>();
            notificationsObjectMap.put("userName", user.getUsername());
            notificationsObjectMap.put("userId", user.getUserId());
            notificationsObjectMap.put("notRead", notRead);
            List<Map<String, Object>> rs = new ArrayList<>();
            if (notificationsObjectArrayList != null && !notificationsObjectArrayList.isEmpty()) {
                for (Object[] object : notificationsObjectArrayList) {
                    Map<String, Object> notificationMap = new HashMap<>();
                    for (int i = 0; i < labels.length; i++) {
                        notificationMap.put(labels[i], object[i]);
                    }
                    rs.add(notificationMap);
                }
            }
            notificationsObjectMap.put("notifications", rs);
            return notificationsObjectMap;
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "UserID không chính xác!!!");
    }

    @Override
    public int updateNotifications(List<Long> ids, String userName) {
        int count = 0 ;
        for (Long id : ids) {
            int updateNotification = notificationsRepository.setReadNotification(id);
            if(updateNotification > 0 ) count++;
        }
        return count;
    }
}
