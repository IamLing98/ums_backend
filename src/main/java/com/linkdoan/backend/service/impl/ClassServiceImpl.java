package com.linkdoan.backend.service.impl;

import com.linkdoan.backend.dto.ClassDTO;
 import com.linkdoan.backend.model.Class;
 import com.linkdoan.backend.repository.ClassRepository;
import com.linkdoan.backend.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;

@Service
public class ClassServiceImpl implements ClassService {
    @Autowired
    ClassRepository classRepository;

    private static final String CLASS = "Class";



    private int checkExist(ClassDTO classDTO) {
        int result = 0 ;
        if (null != classDTO.getClassId() && ""!= classDTO.getClassId()) {
            Class classModel = classRepository.findClassByClassId(classDTO.getClassId());
            if (null == classModel) {
                result = 0;
            }else{
                result = 1;
            }
        }
        return result;
    }

    @Override
    public Page findBy(Pageable pageable, ClassDTO classDTO) {
        return null;
    }
    @Override
    public Class createClass(ClassDTO classDTO){
        if(checkExist( classDTO) == 1 ) throw  new EntityExistsException("Lớp học này đã tồn tại");
        else{
            return classRepository.save(classDTO.toModel());
        }
    }

    @Override
    public Class updateClass(ClassDTO classDTO) {
        if(checkExist( classDTO) == 0 ) throw  new EntityExistsException("Lớp học này không tồn tại");
        Class classModel = classRepository.save(classDTO.toModel());
        return classModel;
    }

    @Override
    public int deleteClass(ClassDTO classDTO) {
        if(checkExist(classDTO) == 0)  return 0;
        classRepository.delete(classDTO.toModel());
        return 1;
    }

}
