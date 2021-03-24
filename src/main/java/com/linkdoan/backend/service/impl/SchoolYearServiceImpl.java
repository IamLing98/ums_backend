package com.linkdoan.backend.service.impl;

import com.linkdoan.backend.dto.SchoolYearDTO;
import com.linkdoan.backend.model.SchoolYear;
import com.linkdoan.backend.repository.SchoolYearRepository;
import com.linkdoan.backend.service.SchoolYearService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class SchoolYearServiceImpl implements SchoolYearService {

    @Autowired
    SchoolYearRepository schoolYearRepository;

    @Override
    public List<SchoolYearDTO> getAll() {
        List<SchoolYear> schoolYears = schoolYearRepository.findAll();
        List<SchoolYearDTO> schoolYearDTOS = schoolYears.stream().map(schoolYear -> schoolYear.toDTO()).collect(Collectors.toList());
        return schoolYearDTOS;
    }
}
