package com.linkdoan.backend.controller;

import com.linkdoan.backend.dto.EducationProgramSubjectDTO;
import com.linkdoan.backend.service.EducationProgramSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EducationProgramSubjectController {

    @Autowired
    EducationProgramSubjectService educationProgramSubjectService;

    @RequestMapping(value = "/education-program-subject", method = RequestMethod.GET)
    public ResponseEntity<?> getListSubject(@RequestParam(name="educationProgramId", required = true) String educationProgramId,
                                            @RequestParam(name="in", required = true) boolean isIn
                                            ) throws Exception {
        return new ResponseEntity<>(educationProgramSubjectService.getListSubjectCorrectWithEP(educationProgramId,isIn), HttpStatus.OK);
    }

    @RequestMapping(value = "/education-program-subject", method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody List<EducationProgramSubjectDTO> educationProgramSubjectDTOList) throws Exception {
        return new ResponseEntity<>(educationProgramSubjectService.create(educationProgramSubjectDTOList), HttpStatus.OK);
    }

    @RequestMapping(value = "/education-program-subject/{educationProgramId}/{subjectId}", method = RequestMethod.PUT)
    public ResponseEntity<?> change(@PathVariable("educationProgramId") String educationProgramId,
                                    @PathVariable("subjectId") String subjectId,
                                    @RequestBody EducationProgramSubjectDTO educationProgramSubjectDTO) throws Exception {
        return new ResponseEntity<>(educationProgramSubjectService.change(educationProgramId, subjectId, educationProgramSubjectDTO), HttpStatus.OK);
    }


    @RequestMapping(value = "/education-program-subject/{educationProgramId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable("educationProgramId") String educationProgramId,  @RequestParam("ids") List<Long> ids) throws Exception {
        return new ResponseEntity<>(educationProgramSubjectService.delete(educationProgramId,ids), HttpStatus.OK);
    }

}
