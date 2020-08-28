package com.linkdoan.backend.service;

import com.linkdoan.backend.model.Admissions;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;

@ComponentScan(basePackages= {"com.linkdoan.backend.*"})
public interface AdmissionService {
    List<Admissions> getAllActiveAdmission();
}
