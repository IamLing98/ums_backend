package com.linkdoan.backend.service;

import com.linkdoan.backend.dto.YearClassDTO;
import com.linkdoan.backend.model.YearClass;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;

import java.io.IOException;

@ComponentScan(basePackages= {"com.linkdoan.backend.*"})
public interface ClassService {
    public Page findBy(String classId, String departmentId, Integer startYear, Integer page, Integer pageSize);
    public YearClass createClass(YearClassDTO yearClassDTO);
    public YearClass updateYearClass(YearClassDTO yearClassDTO);
    public int deleteClass(YearClassDTO yearClassDTO);
    YearClassDTO getDetails(String classId) throws IOException;
}
