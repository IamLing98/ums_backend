package com.linkdoan.backend.service.impl;

import com.linkdoan.backend.dto.ClassDTO;
import com.linkdoan.backend.model.Class;
import com.linkdoan.backend.model.Department;
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
import javax.persistence.EntityNotFoundException;
import java.util.List;
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
        String departmentId = classDTO.getDepartmentId();
        Optional<Department> optionalDepartment = departmentRepository.findFirstByDepartmentId(departmentId);
        if (optionalDepartment.isPresent()) {
            Department department = optionalDepartment.get();
            classModel.setDepartment(department);
            result = classRepository.save(classModel);
            List<Class> classList = department.getChildren();
            classList.add(classModel);
            department.setChildren(classList);
        } else throw new EntityNotFoundException("Khoa này không tồn tại");
        return result;
    }

    @Override
    public Class updateClass(ClassDTO classDTO) {
        Class classModel = classDTO.toModel();
        Class result;
        if (!classRepository.existsById(classDTO.getClassId()))
            throw new EntityExistsException("Lớp học này không tồn tại");
        Optional<Department> optionalDepartment = departmentRepository.findFirstByDepartmentId(classDTO.getDepartmentId());
        if (optionalDepartment.isPresent()) {
            Department department = optionalDepartment.get();
            classModel.setDepartment(department);
            result = classRepository.save(classModel);
            List<Class> classList = department.getChildren();
            classList.add(classModel);
            department.setChildren(classList);
        }
        else throw new EntityNotFoundException("Khoa này không tồn tại");
        return result;
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
