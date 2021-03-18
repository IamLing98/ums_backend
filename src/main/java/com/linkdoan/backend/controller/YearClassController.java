package com.linkdoan.backend.controller;

import com.linkdoan.backend.dto.YearClassDTO;
import com.linkdoan.backend.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class YearClassController {

    @Autowired
    private ClassService classService;

    @GetMapping("/yearClasses")
    public ResponseEntity<?> getAll() throws Exception {
        return new ResponseEntity<>(classService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/yearClasses/{yearClassId}")
    public ResponseEntity<?> getDetail(@PathVariable("yearClassId") String yearClassId) throws Exception {
        return new ResponseEntity<>(classService.getDetail(yearClassId), HttpStatus.OK);
    }

    @PostMapping("/yearClasses")
    public ResponseEntity<?> create(@RequestBody YearClassDTO yearClassDTO) throws Exception {
        return new ResponseEntity<>(classService.create(yearClassDTO), HttpStatus.OK);
    }

    @PutMapping("/yearClasses")
    public ResponseEntity<?> update(@RequestBody YearClassDTO yearClassDTO) throws Exception {
        return new ResponseEntity<>(classService.update(yearClassDTO), HttpStatus.OK);
    }

    @DeleteMapping("/yearClasses/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) throws Exception {
        return new ResponseEntity<>(classService.delete(id), HttpStatus.OK);
    }
}
