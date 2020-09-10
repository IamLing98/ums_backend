package com.linkdoan.backend.controller;

import com.linkdoan.backend.dto.EducationProgramDTO;
import com.linkdoan.backend.model.Role;
import com.linkdoan.backend.repository.RoleRepository;
import com.linkdoan.backend.service.impl.EducationProgramServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class EducationProgramController {

    @Autowired
    EducationProgramServiceImpl educationProgramServiceImpl ;

    @Autowired
    RoleRepository roleRepository;

    @RequestMapping(value = "/education-program/getAllProgram", method = RequestMethod.GET)
    public ResponseEntity<?> getAll() throws Exception {
        List<EducationProgramDTO> rs = educationProgramServiceImpl.getAllProgram();
        return new ResponseEntity<>(rs, HttpStatus.OK);
    }

    @RequestMapping(value = "/education-program/getAllRoles", method = RequestMethod.GET)
    public ResponseEntity<?> getAllRoles() throws Exception {
        List<Role> rs = roleRepository.findAllRoles((long) 1);
        return new ResponseEntity<>(rs, HttpStatus.OK);
    }

    @RequestMapping(value="/education-program/create",method = RequestMethod.POST)
    public ResponseEntity<?> create(@Valid @ModelAttribute EducationProgramDTO educationProgramDTO) throws Exception {
        return new ResponseEntity<>(educationProgramServiceImpl.createNewEducationProgram(educationProgramDTO), HttpStatus.OK);
    }

    @RequestMapping(value="/education-program/update",method = RequestMethod.POST)
    public ResponseEntity<?> update(@Valid @ModelAttribute EducationProgramDTO educationProgramDTO) throws Exception {
        return new ResponseEntity<>(educationProgramServiceImpl.updateEducationProgram(educationProgramDTO), HttpStatus.OK);
    }

    @RequestMapping(value="/education-program/delete",method = RequestMethod.POST)
    public ResponseEntity<?> delete(@RequestBody List<EducationProgramDTO> educationProgramDTOList) throws Exception {
        if (educationProgramServiceImpl.delete(educationProgramDTOList) == false)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value="/education-program/updateSubject",   consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> updateSubject(@RequestBody EducationProgramDTO educationProgramDTO ) throws Exception {
        return new ResponseEntity<>(educationProgramServiceImpl.updateEducationProgramSubject(educationProgramDTO), HttpStatus.OK);
    }

    @RequestMapping(value = "/education-program/getDetails", method = RequestMethod.POST)
    public ResponseEntity<?> getDetails(@RequestBody @Valid EducationProgramDTO educationProgramDTO) throws Exception {

        return new ResponseEntity<>(educationProgramServiceImpl.getDetails(educationProgramDTO), HttpStatus.OK);
    }
}
