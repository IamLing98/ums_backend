package com.linkdoan.backend.controller;

import com.linkdoan.backend.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
public class ScheduleController {
    @Autowired
    ScheduleService scheduleServiceService;

    @RequestMapping(value = "/schedules", method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody String termId)
            throws Exception {
        return new ResponseEntity<>(scheduleServiceService.initData(termId), HttpStatus.OK);
    }

    @GetMapping(value = "/schedules/{termId}")
    public ResponseEntity<?> getSchedule(@PathVariable String termId, SecurityContextHolder request)
            throws Exception {
        String teacherId = SecurityContextHolder.getContext().getAuthentication().getName();
        return new ResponseEntity<>(scheduleServiceService.getSchedule(teacherId, termId), HttpStatus.OK);
    }

}
