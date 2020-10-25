package com.linkdoan.backend.service.impl;

import com.linkdoan.backend.controller.SubjectRegistrationController;
import com.linkdoan.backend.dto.SubjectRegistrationDTO;
import com.linkdoan.backend.repository.SubjectRegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectRegistrationServiceImpl extends SubjectRegistrationController {

    @Autowired
    SubjectRegistrationRepository subjectRegistrationRepository;

    List<SubjectRegistrationDTO> subjectRegistrationDTOList(){
        return

    }
}
