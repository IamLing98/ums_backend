package com.linkdoan.backend.controller;

import com.linkdoan.backend.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ScheduleController {
    @Autowired
    ScheduleService scheduleServiceService;

    @RequestMapping(value = "/schedules", method = RequestMethod.GET)
    public ResponseEntity<?> create ()
            throws Exception {
        return new ResponseEntity<>(scheduleServiceService.initData(), HttpStatus.OK);
    }
}