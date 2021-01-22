package com.linkdoan.backend.controller;

import com.linkdoan.backend.dto.YearClassDTO;
import com.linkdoan.backend.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class YearClassController {

    @Autowired
    private ClassService classService;

    @GetMapping("/yearClasses")
    public ResponseEntity<?> getAll() throws Exception {
        return new ResponseEntity<>(classService.getAll(), HttpStatus.OK);
    }

    @PostMapping("/yearClasses")
    public ResponseEntity<?> create(@RequestBody YearClassDTO yearClassDTO) throws Exception {
        return new ResponseEntity<>(classService.create(yearClassDTO), HttpStatus.OK);
    }
    //details
//    @RequestMapping(value = "/yearClasses/details", method = RequestMethod.GET)
//    public ResponseEntity<?> getDetails(@Param("classId") String classId) throws Exception {
//        return new ResponseEntity<>(classService.getDetails(classId), HttpStatus.OK);
//    }
//
//    @RequestMapping(value = "/yearClasses", method = RequestMethod.POST)
//    public ResponseEntity<?> create(@RequestBody YearClassDTO yearClassDTO) throws Exception {
//        return new ResponseEntity<>(classService.create(yearClassDTO), HttpStatus.OK);
//    }
//
//    @RequestMapping(value = "/yearClass", method = RequestMethod.PUT)
//    public ResponseEntity<?> update(@RequestBody YearClassDTO yearClassDTO) throws Exception {
//        return new ResponseEntity<>(classService.updateYearClass(yearClassDTO), HttpStatus.OK);
//    }
//
//    @RequestMapping(value = "/yearClass", method = RequestMethod.DELETE)
//    public ResponseEntity<?> delete(@RequestBody YearClassDTO yearClassDTO) throws Exception {
//        return new ResponseEntity<>(classService.deleteClass(yearClassDTO), HttpStatus.OK);
//    }
}
