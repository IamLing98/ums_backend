package com.linkdoan.backend.controller;

import com.linkdoan.backend.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ScheduleController {
    @Autowired
    ScheduleService scheduleServiceService;


    @RequestMapping(value = "/schedules", method = RequestMethod.GET)
    public ResponseEntity<?> getSchedule (@RequestParam(value = "termId", required = true) String termId)
            throws Exception {
        return new ResponseEntity<>(scheduleServiceService.getSchedule(termId), HttpStatus.OK);
    }

    @RequestMapping(value = "/schedules/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getScheduleInfo (@PathVariable(  "id") Long id)
            throws Exception {
        return new ResponseEntity<>(scheduleServiceService.getScheduleInfo(id), HttpStatus.OK);
    }


    @RequestMapping(value = "/schedules", method = RequestMethod.POST)
    public ResponseEntity<?> create (@RequestBody String termId)
            throws Exception {
        return new ResponseEntity<>(scheduleServiceService.initData(termId), HttpStatus.OK);
    }
}
