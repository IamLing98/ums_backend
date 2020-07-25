package com.linkdoan.backend.service.impl;

import com.linkdoan.backend.dto.DepartmentDTO;
import com.linkdoan.backend.dto.SpecializedDTO;
import com.linkdoan.backend.model.Department;
import com.linkdoan.backend.model.Specialized;
import com.linkdoan.backend.repository.DepartmentRepository;
import com.linkdoan.backend.repository.SpecializedRepository;
import com.linkdoan.backend.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service("departmentService")
public class DepartmentServiceImpl implements DepartmentService {

    @Qualifier("departmentRepository")
    @Autowired
    private DepartmentRepository departmentRepository;

    @Qualifier("specializedRepository")
    @Autowired
    private SpecializedRepository specializedRepository;

    @Override
    public List<DepartmentDTO> getAllDepartment(String departmentId) throws IOException {
        List<Department> departments = departmentRepository.findBy(departmentId);
        List<DepartmentDTO> departmentDTOList = new ArrayList<>();
        for (int i = 0; i < departments.size(); i++) {
            departmentDTOList.add(departments.get(i).toDTO());
        }
        return departmentDTOList;
    }

    @Override
    public Page<Specialized> getAllSpecialized(SpecializedDTO specializedDTO) throws IOException {
        Pageable pageable = PageRequest.of(specializedDTO.getPage(), specializedDTO.getPageSize());
        return specializedRepository.findBy(specializedDTO.getSpecializedId(), specializedDTO.getDepartmentId(), pageable);
    }
}
