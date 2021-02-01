package com.linkdoan.backend.controller;

import com.linkdoan.backend.dto.TermDTO;
import com.linkdoan.backend.service.TermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TermController {
    @Autowired
    TermService termService;

    @RequestMapping(value = "/terms", method = RequestMethod.GET)
    public ResponseEntity<?> findBy(@RequestParam(name = "year", required = false) Integer year,
                                    @RequestParam(name = "term", required = false) Integer term)
            throws Exception {
        return new ResponseEntity<>(termService.getAll(year, term), HttpStatus.OK);
    }

    @RequestMapping(value = "/terms/{termId}", method = RequestMethod.GET)
    public ResponseEntity<?> getDetail(@PathVariable("termId") String termId)
            throws Exception {
        return new ResponseEntity<>(termService.getDetail(termId), HttpStatus.OK);
    }

    @RequestMapping(value = "/terms", method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody TermDTO termDTO)
            throws Exception {
        return new ResponseEntity<>(termService.create(termDTO), HttpStatus.OK);
    }

    @RequestMapping(value = "/terms/{termId}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@PathVariable("termId") String termId, @RequestBody TermDTO termDTO)
            throws Exception {
        //        Example<Student> searchTerm = Example.of(new Student());
        return new ResponseEntity<>(termService.update(termId, termDTO), HttpStatus.OK);
    }

//    @CrossOrigin(origins = "http://localhost:3000")
    @DeleteMapping(value = "/terms/{termId}")
    public ResponseEntity<?> deleteTerm(@PathVariable("termId") String termId)
            throws Exception {
        //        Example<Student> searchTerm = Example.of(new Student());
        return new ResponseEntity<>(termService.delete(termId), HttpStatus.OK);
    }


}
