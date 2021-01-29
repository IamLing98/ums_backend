package com.linkdoan.backend.controller;

import com.linkdoan.backend.dto.SubjectClassDTO;
import com.linkdoan.backend.service.SubjectClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SubjectClassController {
    @Autowired
    SubjectClassService subjectClassService;

    @RequestMapping(value = "/subjectClasses/{termId}", method = RequestMethod.GET)
    public ResponseEntity<?> findBy(@PathVariable("termId") String termId)
            throws Exception {
        return new ResponseEntity<>(subjectClassService.getAll(termId), HttpStatus.OK);
    }

    @RequestMapping(value = "/subjectClasses/getDetail/{subjectClassId}", method = RequestMethod.GET)
    public ResponseEntity<?> getDetail( @PathVariable("subjectClassId") String subjectClassId)
            throws Exception {
        return new ResponseEntity<>(subjectClassService.getDetail(subjectClassId), HttpStatus.OK);
    }

    @RequestMapping(value = "/subjectClasses", method = RequestMethod.POST)
    public ResponseEntity<?> createMore(@RequestBody List<SubjectClassDTO> subjectClassDTOList)
            throws Exception {
        return new ResponseEntity<>(subjectClassService.create(subjectClassDTOList), HttpStatus.OK);
    }

    @RequestMapping(value = "/subjectClasses/{subjectClassId}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@PathVariable("subjectClassId") String subjectClassId, @RequestBody SubjectClassDTO subjectClassDTO)
            throws Exception {
        return new ResponseEntity<>(subjectClassService.update(subjectClassId, subjectClassDTO), HttpStatus.OK);
    }

    @DeleteMapping(value = "/subjectClasses/{subjectClassId}")
    public ResponseEntity<?> deleteTerm(@PathVariable("subjectClassId") String subjectClassId)
            throws Exception {
        return new ResponseEntity<>(subjectClassService.delete(subjectClassId), HttpStatus.OK);
    }

}
