package com.linkdoan.backend.service.impl;

import com.linkdoan.backend.dto.StudentDTO;
import com.linkdoan.backend.model.Class;
import com.linkdoan.backend.model.Department;
import com.linkdoan.backend.model.Student;
import com.linkdoan.backend.repository.ClassRepository;
import com.linkdoan.backend.repository.DepartmentRepository;
import com.linkdoan.backend.repository.StudentRepository;
import com.linkdoan.backend.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service("studentService")
public class StudentServiceImpl implements StudentService  {

    private static final String STUDENT = "Student";

    @Qualifier("studentRepository")
    @Autowired
    private StudentRepository studentRepository;

    @Qualifier("departmentRepository")
    @Autowired
    private DepartmentRepository departmentRepository;

    @Qualifier("classRepository")
    @Autowired
    private ClassRepository classRepository;

    @Override
    public Page findBy( StudentDTO studentDTO) throws IOException{
        Pageable pageable = PageRequest.of(studentDTO.getPage(), studentDTO.getPageSize());
        return studentRepository.findBy(studentDTO.getKeySearch1(), studentDTO.getKeySearch2(),studentDTO.getKeySearch3(),studentDTO.getKeySearch4(),studentDTO.getKeySearch5() ,pageable);
    }

    @Override
    public Student insertStudent(StudentDTO studentDTO) throws ParseException {
        Student studentModel  = studentDTO.toModel();
        String classId = studentDTO.getClassId();
        String departmentId = studentDTO.getDepartmentId();
        String courseNumber = studentDTO.getKeySearch1();
        Pageable pageable = PageRequest.of(0, 1);
        String studentId = "";
        Page<Class> classes = classRepository.findBy(classId, departmentId, courseNumber, pageable);
        if(classes.getTotalElements() < 1) throw  new EntityNotFoundException("Lớp học không hợp lệ !!!");
        else{
            List<Class> classList = classes.getContent();
            Integer subDepartmentId = Integer.parseInt(departmentId.substring(4));
            Integer currentNumber = classList.get(0).getYearStart() % 1000;
            Integer nextVal = classList.get(0).getNextVal();
            if((int)nextVal / 100 >= 1) studentId ="5" + currentNumber.toString() +  subDepartmentId.toString() +   String.format("%00d",nextVal);
            else if(nextVal / 10 >= 1 ) studentId ="5" + currentNumber.toString() +  subDepartmentId.toString() +   String.format("%01d",nextVal);
            else studentId ="5" + currentNumber.toString() +  subDepartmentId.toString() +   String.format("%02d",nextVal);
        }
        studentModel.setStudentId(studentId);
        studentModel.setStatus(1);
        java.time.LocalDate date = java.time.LocalDate.now();
        java.sql.Date sqlDate = java.sql.Date.valueOf(date);
        studentModel.setStartSchool(sqlDate);

        return studentRepository.save(studentModel);
    }

    @Override
    public Student updateStudent(StudentDTO studentDTO) throws IOException, ParseException {
        if(!studentRepository.existsById(studentDTO.getStudentId() ) ) throw  new EntityExistsException("Sinh viên này chưa có trên hệ thống");
        Class classModel = classRepository.findFirstByClassId(studentDTO.getClassId());
        Department department = departmentRepository.findFirstByDepartmentId(studentDTO.getDepartmentId());
        return studentRepository.save(studentDTO.toModel( ));
    }
    @Override
    public int deleteStudent(StudentDTO studentDTO) throws IOException, ParseException {
        if(!studentRepository.existsById(studentDTO.getStudentId() ) ) throw  new EntityExistsException("Sinh viên này chưa có trên hệ thống");
        studentRepository.delete(studentDTO.toModel(  ));
        return 1;
    }



}
