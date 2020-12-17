package com.linkdoan.backend.controller;

import com.linkdoan.backend.dto.TermDTO;
import com.linkdoan.backend.service.TermService;
import com.linkdoan.backend.service.impl.TermServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class TermController {
    @Autowired
    TermService termService;

    @RequestMapping(value = "/terms", method = RequestMethod.GET)
    public ResponseEntity<?> findBy(@RequestParam(name = "year", required = false) Integer year,
                                    @RequestParam(name = "term", required = false) Integer term)
            throws Exception {
        //        Example<Student> searchTerm = Example.of(new Student());
        return new ResponseEntity<>(termService.getAll(year, term), HttpStatus.OK);
    }

    @RequestMapping(value = "/terms/{termId}", method = RequestMethod.GET)
    public ResponseEntity<?> getDetail(@PathVariable("termId") String termId)
            throws Exception {
        Optional<TermDTO> termDTOOptional = termService.getDetail(termId);
        return new ResponseEntity<>(termService.getDetail(termId).get(), HttpStatus.OK);
    }

    @RequestMapping(value = "/terms", method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody TermDTO termDTO)
            throws Exception {
        //        Example<Student> searchTerm = Example.of(new Student());
        return new ResponseEntity<>(termService.create(termDTO), HttpStatus.OK);
    }

    @RequestMapping(value = "/terms", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@RequestBody TermDTO termDTO)
            throws Exception {
        //        Example<Student> searchTerm = Example.of(new Student());
        return new ResponseEntity<>(termService.update(termDTO), HttpStatus.OK);
    }

    @RequestMapping(value = "/terms", method = RequestMethod.DELETE)
    public ResponseEntity<?> update(@RequestParam(name = "id", required = true) String id)
            throws Exception {
        //        Example<Student> searchTerm = Example.of(new Student());
        return new ResponseEntity<>(termService.delete(id), HttpStatus.OK);
    }


}
