package com.linkdoan.backend.service;

import com.linkdoan.backend.dto.ClassDTO;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.linkdoan.backend.model.Class;

import java.util.Optional;

@ComponentScan(basePackages= {"com.linkdoan.backend.*"})
public interface ClassService {
    public Page findBy(ClassDTO classDTO);
    public Class createClass(ClassDTO classDTO);
    public Class updateClass(ClassDTO classDTO);
    public int deleteClass(ClassDTO classDTO);
}
