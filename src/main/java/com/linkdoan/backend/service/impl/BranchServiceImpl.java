package com.linkdoan.backend.service.impl;

import com.linkdoan.backend.dto.BranchDTO;
import com.linkdoan.backend.repository.BranchRepository;
import com.linkdoan.backend.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BranchServiceImpl implements BranchService {

    @Autowired
    BranchRepository branchRepository;

    public  List<BranchDTO> getAllBranch(String departmentId) {
        return branchRepository.findAllByDepartmentId(departmentId);
    }

    public List<BranchDTO> findAllByDepartmentId(String departmentId){
        return branchRepository.findAllByDepartmentId( departmentId);
    }
}
