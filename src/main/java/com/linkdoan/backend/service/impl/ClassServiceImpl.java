package com.linkdoan.backend.service.impl;

import com.linkdoan.backend.dto.YearClassDTO;
import com.linkdoan.backend.model.YearClass;
import com.linkdoan.backend.repository.DepartmentRepository;
import com.linkdoan.backend.repository.YearClassRepository;
import com.linkdoan.backend.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.util.Optional;

@Service("classService")
public class ClassServiceImpl implements ClassService {

    private static final String CLASS = "Class";

    @Qualifier("yearClassRepository")
    @Autowired
    private YearClassRepository yearClassRepository;

    @Qualifier("departmentRepository")
    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public Page findBy(YearClassDTO yearClassDTO) {
        Pageable pageable = PageRequest.of(yearClassDTO.getPage(), yearClassDTO.getPageSize());
        YearClass yearClass = yearClassDTO.toModel();
        if (yearClass.getBranchId() != "" && yearClass.getBranchId() != null) {

        }
        return yearClassRepository.findBy(yearClassDTO.getClassId(), yearClass.getBranchId(), yearClassDTO.getKeySearch1(), pageable);
    }

    @Override
    public YearClass createClass(YearClassDTO yearClassDTO) {
        if (yearClassRepository.existsById(yearClassDTO.getClassId()))
            throw new EntityExistsException("Lớp học này đã tồn tại");
        YearClass classModel = yearClassDTO.toModel();
        YearClass result;
        return classModel;
    }

    @Override
    public YearClass updateClass(YearClassDTO yearClassDTO) {
        YearClass classModel = yearClassDTO.toModel();
        YearClass result;
        return classModel;
    }

    @Override
    public int deleteClass(YearClassDTO yearClassDTO) {
        if (!yearClassRepository.existsById(yearClassDTO.getClassId()))
            throw new EntityExistsException("Lớp học này không tồn tại");
        else {
            Optional<YearClass> optionalClass = yearClassRepository.findFirstByClassId(yearClassDTO.getClassId());
            yearClassRepository.delete(optionalClass.get());
        }
        return 1;
    }

}
