package com.linkdoan.backend.service;

import com.linkdoan.backend.dto.YearClassDTO;
import com.linkdoan.backend.model.YearClass;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;

@ComponentScan(basePackages= {"com.linkdoan.backend.*"})
public interface ClassService {
    public Page findBy(YearClassDTO yearClassDTO);
    public YearClass createClass(YearClassDTO yearClassDTO);
    public YearClass updateClass(YearClassDTO yearClassDTO);
    public int deleteClass(YearClassDTO yearClassDTO);
}
