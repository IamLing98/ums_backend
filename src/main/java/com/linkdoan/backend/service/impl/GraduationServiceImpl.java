package com.linkdoan.backend.service.impl;

import com.linkdoan.backend.dto.GraduationDTO;
import com.linkdoan.backend.repository.GraduationRepository;
import com.linkdoan.backend.service.GraduationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GraduationServiceImpl implements GraduationService {

    @Autowired
    GraduationRepository graduationRepository;

    @Override
    public List<GraduationDTO> getAll() {
        List<GraduationDTO> graduationDTOList = graduationRepository.getAll();
        return graduationDTOList;
    }

}
