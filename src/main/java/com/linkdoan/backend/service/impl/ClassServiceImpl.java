package com.linkdoan.backend.service.impl;

import com.linkdoan.backend.dto.ClassDTO;
import com.linkdoan.backend.model.Class;
import com.linkdoan.backend.repository.ClassRepository;
import com.linkdoan.backend.repository.DepartmentRepository;
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

    @Qualifier("classRepository")
    @Autowired
    private ClassRepository classRepository;

    @Qualifier("departmentRepository")
    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public Page findBy(ClassDTO classDTO) {
        Pageable pageable = PageRequest.of(classDTO.getPage(), classDTO.getPageSize());
        if (classDTO.getDepartmentId() != "" && classDTO.getDepartmentId() != null) {

        }
        return classRepository.findBy(classDTO.getClassId(), classDTO.getDepartmentId(), classDTO.getKeySearch1(), pageable);
    }

    @Override
    public Class createClass(ClassDTO classDTO) {
        if (classRepository.existsById(classDTO.getClassId()))
            throw new EntityExistsException("Lớp học này đã tồn tại");
        Class classModel = classDTO.toModel();
        Class result;
        return classModel;
    }

    @Override
    public Class updateClass(ClassDTO classDTO) {
        Class classModel = classDTO.toModel();
        Class result;
        return classModel;
    }

    @Override
    public int deleteClass(ClassDTO classDTO) {
        if (!classRepository.existsById(classDTO.getClassId()))
            throw new EntityExistsException("Lớp học này không tồn tại");
        else {
            Optional<Class> optionalClass = classRepository.findFirstByClassId(classDTO.getClassId());
            classRepository.delete(optionalClass.get());
        }
        return 1;
    }

}
