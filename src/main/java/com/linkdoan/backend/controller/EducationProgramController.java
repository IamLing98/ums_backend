package com.linkdoan.backend.controller;

import com.linkdoan.backend.dto.EducationProgramDTO;
import com.linkdoan.backend.repository.RoleRepository;
import com.linkdoan.backend.service.impl.EducationProgramServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EducationProgramController {

    @Autowired
    EducationProgramServiceImpl educationProgramServiceImpl;

    @Autowired
    RoleRepository roleRepository;

    @RequestMapping(value = "/education-programs", method = RequestMethod.GET)
    public ResponseEntity<?> getAll(@RequestParam(name = "educationProgramId", required = false) String educationProgramId, @RequestParam(name = "branchId", required = false) String branchId) throws Exception {
        List<EducationProgramDTO> rs = educationProgramServiceImpl.getAllProgram(branchId, educationProgramId);
        return new ResponseEntity<>(rs, HttpStatus.OK);
    }

    @RequestMapping(value = "/education-programs/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getDetail(@PathVariable("id") String educationProgramId) throws Exception {
        return new ResponseEntity<>(educationProgramServiceImpl.getDetail(educationProgramId), HttpStatus.OK);
    }

    @RequestMapping(value = "/education-programs", method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody EducationProgramDTO educationProgramDTO) throws Exception {
        return new ResponseEntity<>(educationProgramServiceImpl.create(educationProgramDTO), HttpStatus.OK);
    }

    @PutMapping(value = "/education-programs/{id}")
    public ResponseEntity<?> update(@PathVariable("id") String id, @RequestBody EducationProgramDTO educationProgramDTO) throws Exception {
        return new ResponseEntity<>(educationProgramServiceImpl.update(id, educationProgramDTO), HttpStatus.OK);
    }

    @RequestMapping(value = "/education-program/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable String id) throws Exception {
        if (educationProgramServiceImpl.delete(id) == false)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(HttpStatus.OK);
    }
}
