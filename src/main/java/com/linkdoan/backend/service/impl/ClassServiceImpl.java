package com.linkdoan.backend.service.impl;

import com.linkdoan.backend.dto.YearClassDTO;
import com.linkdoan.backend.model.Department;
import com.linkdoan.backend.model.YearClass;
import com.linkdoan.backend.model.primaryKey.DepartmentCourseNextVal;
import com.linkdoan.backend.repository.DepartmentCourseNextValRepository;
import com.linkdoan.backend.repository.DepartmentRepository;
import com.linkdoan.backend.repository.YearClassRepository;
import com.linkdoan.backend.service.ClassService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;
import java.util.Optional;

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
        Optional<Department> departmentOptional = departmentRepository.findById(yearClassDTO.getDepartmentId());
        if (departmentOptional.isPresent()) {
            Department department = departmentOptional.get();
            Integer courseNumber = yearClassDTO.getStartYear() - department.getStartYear() + 1;
            DepartmentCourseNextVal departmentCourseNextVal = departmentCourseNextValRepository.findDistinctFirstByDepartmentIdAndAndCourseNumber(yearClassDTO.getDepartmentId(), courseNumber);
            if (departmentCourseNextVal != null) {
                Integer modStarYear = yearClassDTO.getStartYear() % 2000;
                String modDepartmentId = department.getDepartmentId().substring(4, 6);
                String classId = "5" + modStarYear + modDepartmentId + departmentCourseNextVal.getNextClassValue();
                yearClassDTO.setClassId(classId);
                yearClassDTO.setClassName(department.getDepartmentName());
                yearClassDTO.setTotalMember(0);
                yearClassDTO.setCurrentTerm(1);
                yearClassDTO.setCourseNumber(courseNumber);
                YearClass yearClass = yearClassDTO.toModel();
                yearClassRepository.save(yearClass);
                departmentCourseNextVal.setNextClassValue(departmentCourseNextVal.getNextClassValue() + 1);
                departmentCourseNextValRepository.save(departmentCourseNextVal);
            }
        } else throw new ResponseStatusException(HttpStatus.CONFLICT, "Không hợp lệ!!!");
        return 1;
    }

    @Override
    public int update(YearClassDTO yearClassDTO) {
        Optional<YearClass> yearClassOptional = yearClassRepository.findById(yearClassDTO.getClassId());
        if (yearClassOptional.isPresent()) {
            YearClass yearClass = yearClassDTO.toModel();
            yearClassRepository.save(yearClass);
            return 1;
        } else throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Không tồn tại!!!");

    }

    @Override
    public int delete(String id) {
        Optional<YearClass> yearClassOptional = yearClassRepository.findById(id);
        if (yearClassOptional.isPresent()) {
            yearClassRepository.deleteById(id);
            return 1;
        } else throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Không tồn tại!!!");
    }
}
