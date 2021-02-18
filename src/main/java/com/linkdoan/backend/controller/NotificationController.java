package com.linkdoan.backend.controller;

import com.linkdoan.backend.service.NotificationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class NotificationController {
    @Autowired
    private NotificationsService notificationsService;

    @GetMapping(value = "/notifications")
    public ResponseEntity<?> getAllNotifications(SecurityContextHolder request) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(auth.getDetails());
        return new ResponseEntity<>(notificationsService.getAllNotifications(request.getContext().getAuthentication().getName()), HttpStatus.OK);
    }

    @PutMapping(value="/notifications")
    public ResponseEntity<?> editNotification(@RequestBody List<Long> ids, SecurityContextHolder request) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(auth.getDetails());
        return new ResponseEntity<>(notificationsService.updateNotifications(ids,request.getContext().getAuthentication().getName()), HttpStatus.OK);
    }

}
