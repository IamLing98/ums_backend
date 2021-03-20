package com.linkdoan.backend.service;

import com.linkdoan.backend.dto.GraduationDTO;
import com.linkdoan.backend.model.Graduation;

import java.util.List;

public interface GraduationService {

    List<GraduationDTO> getAll();
}
