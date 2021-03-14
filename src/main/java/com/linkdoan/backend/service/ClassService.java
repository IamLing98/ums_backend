package com.linkdoan.backend.service;

import com.linkdoan.backend.dto.YearClassDTO;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;
import java.util.Map;

@ComponentScan(basePackages = {"com.linkdoan.backend.*"})
public interface ClassService {
    List<YearClassDTO> getAll();

    YearClassDTO getDetail(String yearClassId);

    int create(YearClassDTO yearClassDTO);

    int update(YearClassDTO yearClassDTO);

    int delete(String id);
}
