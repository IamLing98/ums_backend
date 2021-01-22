package com.linkdoan.backend.service.impl;

import com.linkdoan.backend.dto.YearClassDTO;
import com.linkdoan.backend.model.Department;
import com.linkdoan.backend.model.primaryKey.DepartmentCourseNextVal;
import com.linkdoan.backend.repository.DepartmentCourseNextValRepository;
import com.linkdoan.backend.repository.DepartmentRepository;
import com.linkdoan.backend.repository.YearClassRepository;
import com.linkdoan.backend.service.ClassService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("classService")
public class ClassServiceImpl implements ClassService {

    private static Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

    @Autowired
    private YearClassRepository yearClassRepository;

    @Qualifier("departmentRepository")
    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private StudentServiceImpl studentService;

    @Autowired
    private EducationProgramServiceImpl educationProgramService;


    @Override
    public List<YearClassDTO> getAll() {
        List<YearClassDTO> yearClassDTOS = yearClassRepository.getAll();
        return yearClassDTOS;
    }

    @Override
    public List<Map<String, Object>> getDetail(String id) {
        return null;
    }

    @Autowired
    DepartmentCourseNextValRepository departmentCourseNextValRepository;

    @Override
    public int create(YearClassDTO yearClassDTO) {
        List<Department> departmentList = departmentRepository.findAll();
        for(Department department : departmentList){
            int max = 2100 - department.getStartYear();
            for(int i = 1; i < max; i++){
                DepartmentCourseNextVal departmentCourseNextVal = new DepartmentCourseNextVal();
                departmentCourseNextVal.setDepartmentId(department.getDepartmentId());
                departmentCourseNextVal.setCourseNumber(i);
                departmentCourseNextVal.setNextClassValue(0);
                departmentCourseNextValRepository.save(departmentCourseNextVal);
            }
        }
        return 0;
    }

    @Override
    public int update(YearClassDTO yearClassDTO) {
        return 0;
    }

    @Override
    public int delete(String id) {
        return 0;
    }
}
