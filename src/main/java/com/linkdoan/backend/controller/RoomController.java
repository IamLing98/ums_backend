package com.linkdoan.backend.controller;

import com.linkdoan.backend.dto.RoomDTO;
import com.linkdoan.backend.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class RoomController {

    @Autowired
    RoomService roomService;

    @RequestMapping(value = "/rooms", method = RequestMethod.GET)
    public ResponseEntity<?> getAll()
            throws Exception {
        return new ResponseEntity<>(roomService.getAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/rooms", method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody RoomDTO roomDTO)
            throws Exception {
        return new ResponseEntity<>(roomService.create(roomDTO), HttpStatus.OK);
    }

    @RequestMapping(value = "/rooms/{roomId}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@PathVariable("roomId") String roomId, @RequestBody RoomDTO roomDTO)
            throws Exception {
        return new ResponseEntity<>(roomService.update(roomId, roomDTO), HttpStatus.OK);
    }

    @DeleteMapping(value = "/rooms/{roomId}")
    public ResponseEntity<?> deleteTerm(@PathVariable("roomId") String roomId)
            throws Exception {
        return new ResponseEntity<>(roomService.delete(roomId), HttpStatus.OK);
    }

}
