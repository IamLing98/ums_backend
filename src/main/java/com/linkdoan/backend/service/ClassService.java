package com.linkdoan.backend.service;

import com.linkdoan.backend.dto.ClassDTO;
import com.linkdoan.backend.model.Class;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;

@ComponentScan(basePackages= {"com.linkdoan.backend.*"})
public interface ClassService {
    public Page findBy(ClassDTO classDTO);
    public Class createClass(ClassDTO classDTO);
    public Class updateClass(ClassDTO classDTO);
    public int deleteClass(ClassDTO classDTO);
}
