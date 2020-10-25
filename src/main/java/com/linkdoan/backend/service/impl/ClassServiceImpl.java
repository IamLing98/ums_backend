package com.linkdoan.backend.service.impl;

import com.linkdoan.backend.dto.StudentDTO;
import com.linkdoan.backend.dto.YearClassDTO;
import com.linkdoan.backend.model.YearClass;
import com.linkdoan.backend.repository.DepartmentRepository;
import com.linkdoan.backend.repository.YearClassRepository;
import com.linkdoan.backend.service.ClassService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityExistsException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service("classService")
public class ClassServiceImpl implements ClassService {

    private static final String CLASS = "Class";

    private static Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

    @Qualifier("yearClassRepository")
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
    public Page findBy(String classId, String departmentId, Integer yearStart, Integer page, Integer pageSize) {
        if(page == null) page = 0;
        if(pageSize == null) pageSize = 999999;
        Pageable pageable = PageRequest.of(page, pageSize);
        return yearClassRepository.findBy(classId, departmentId, yearStart, pageable);
    }

    @Override
    public YearClass createClass(YearClassDTO yearClassDTO) {
        return null;
    }

    public YearClassDTO create(YearClassDTO yearClassDTO) {
        if(yearClassRepository.existsById(yearClassDTO.getClassId()) == true) throw new ResponseStatusException(HttpStatus.FORBIDDEN,"Existed" );
        YearClass yearClass =  yearClassDTO.toModel();
        yearClassRepository.save(yearClass);
        return yearClassDTO;
    }

    @Override
    public YearClass updateYearClass(YearClassDTO yearClassDTO){
        if(yearClassRepository.existsById(yearClassDTO.getClassId()) == false) throw  new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found");
        YearClass yearClass = yearClassDTO.toModel();
        return yearClassRepository.save(yearClass);
    }

    @Override
    public int deleteClass(YearClassDTO yearClassDTO) {
        if (!yearClassRepository.existsById(yearClassDTO.getClassId()))
            throw new EntityExistsException("Lớp học này không tồn tại");
        else {
            Optional<YearClass> optionalClass = yearClassRepository.findFirstByClassId(yearClassDTO.getClassId());
            yearClassRepository.delete(optionalClass.get());
        }
        return 1;
    }

    @Override
    public YearClassDTO getDetails(String classId) throws IOException {
        if (!yearClassRepository.existsById(classId))
            throw new EntityExistsException("Lớp học này không tồn tại");
        Pageable pageable = PageRequest.of(0, 1);
        YearClassDTO yearClassDTO = yearClassRepository.getDetails(classId,pageable ).getContent().get(0);
        List<StudentDTO> studentDTOList = studentService.findAllByClassId(classId).getContent();
        yearClassDTO.setStudentList(studentDTOList);
        return yearClassDTO;
    }




}
