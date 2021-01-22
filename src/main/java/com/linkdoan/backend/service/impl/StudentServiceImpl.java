package com.linkdoan.backend.service.impl;

import com.linkdoan.backend.dto.StudentDTO;
import com.linkdoan.backend.dto.StudentDetailsDTO;
import com.linkdoan.backend.model.Student;
import com.linkdoan.backend.repository.BranchRepository;
import com.linkdoan.backend.repository.DepartmentRepository;
import com.linkdoan.backend.repository.StudentRepository;
import com.linkdoan.backend.repository.YearClassRepository;
import com.linkdoan.backend.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service("studentService")
public class StudentServiceImpl implements StudentService {

    private static final String STUDENT = "Student";

    private static Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

    @Qualifier("studentRepository")
    @Autowired
    private StudentRepository studentRepository;

    @Qualifier("yearClassRepository")
    @Autowired
    private YearClassRepository yearClassRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private BranchRepository branchRepository ;

    @Override
    public List<StudentDTO> findBy(Integer page, Integer pageSize, String studentId, Integer startYear, String classId, String departmentId) throws IOException {
//        if(page == null) page = 0;
       // Pageable pageable = Pageable.unpaged();
//        if(pageSize == null) pageSize = 999999;
//        Pageable pageable = PageRequest.of(page, pageSize);
        //logger.info(studentDTO.getStartYear().toString());
//        return studentRepository.findBy(studentDTO.getKeySearch1(), studentDTO.getKeySearch2(), studentDTO.getKeySearch3(), studentDTO.getKeySearch4(), studentDTO.getKeySearch5(), pageable);
        List<StudentDTO> studentDTOList = studentRepository.getAllStudent();
        return studentDTOList;
    }

    @Override
    public StudentDetailsDTO getDetails(String studentId) {

        return null;
    }

//    @Override
//    public Student insertStudent(StudentDTO studentDTO) throws ParseException {
//        Student studentModel = studentDTO.toModel();
//        String classId = studentDTO.getClassId();
//        String departmentId = studentDTO.getDepartmentId();
//        String studentId = "";
//        Optional<YearClass> optionalClass = classRepository.findFirstByClassId(classId);
//        if (!optionalClass.isPresent()) throw new EntityNotFoundException("Lớp học không tồn tại !!!");
//        else {
//            YearClass classModel = optionalClass.get();
//            Integer subDepartmentId = Integer.parseInt(departmentId.substring(4));
//            Integer currentNumber = classModel.getStartYear() % 1000;
//            Integer nextVal = classModel.getNextVal();
//            if ((int) nextVal / 100 >= 1)
//                studentId = "5" + currentNumber.toString() + subDepartmentId.toString() + String.format("%00d", nextVal);
//            else if (nextVal / 10 >= 1)
//                studentId = "5" + currentNumber.toString() + subDepartmentId.toString() + String.format("%01d", nextVal);
//            else
//                studentId = "5" + currentNumber.toString() + subDepartmentId.toString() + String.format("%02d", nextVal);
//        }
//        studentModel.setStudentId(studentId);
//        studentModel.setStatus(1);
//        java.time.LocalDate date = java.time.LocalDate.now();
//        java.sql.Date sqlDate = java.sql.Date.valueOf(date);
//        studentModel.setStartSchool(sqlDate);
//
//        return studentRepository.save(studentModel);
//    }
//
    @Override
    public StudentDTO update(StudentDTO studentDTO) throws IOException {
        return studentDTO;
    }



}
