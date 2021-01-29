package com.linkdoan.backend.controller;

import com.linkdoan.backend.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @RequestMapping(value = "/schedules/{termId}/{scheduleId}", method = RequestMethod.PUT)
    public ResponseEntity<?> update (@PathVariable("termId") String termId,@PathVariable("scheduleId") Long scheduleId)
            throws Exception {
        return new ResponseEntity<>(scheduleServiceService.update(termId,scheduleId), HttpStatus.OK);
    }

    @RequestMapping(value = "/schedules/{termId}/{scheduleId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete (@PathVariable("termId") String termId,@PathVariable("scheduleId") Long scheduleId, @RequestParam("ids") List<String> ids)
            throws Exception {
        return new ResponseEntity<>(scheduleServiceService.delete(termId,scheduleId,ids), HttpStatus.OK);
    }
}
