package com.linkdoan.backend.service.impl;

import com.linkdoan.backend.dto.StudentDTO;
import com.linkdoan.backend.exception.BusinessException;
import com.linkdoan.backend.model.Student;
import com.linkdoan.backend.repository.StudentRepository;
import com.linkdoan.backend.service.StudentService;
import com.linkdoan.backend.util.MessageUtils;
import com.linkdoan.backend.util.SystemConstants;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.io.IOException;

@Service
public class StudentServiceImpl implements StudentService, SystemConstants {
    @Autowired
    StudentRepository studentRepository;


    private static final String STUDENT = "Student";



    private int checkExist(StudentDTO studentDTO) {
        int result = 0 ;
        if (null != studentDTO.getStudentId() && ""!= studentDTO.getStudentId()) {
            Student student = studentRepository.findByStudentId(studentDTO.getStudentId());
            if (null == student) {
                result = 0;
            }else{
                result = 1;
            }
        }
        return result;
    }


    @Override
    public Page<Student> getListStudent( Pageable pageable) {
          return studentRepository.findAll( pageable);
    }

    @Override
    public Student insertStudent(StudentDTO studentDTO) {
        if(checkExist(studentDTO) == 1) throw  new EntityExistsException("Sinh viên này đã tồn tại");
        return studentRepository.save(studentDTO.toModel());
    }

    @Override
    public Student updateStudent(StudentDTO studentDTO) throws IOException {
        if(checkExist(studentDTO) == 0)  throw  new EntityExistsException("Sinh viên này chưa có trên hệ thống");
        Student student = studentRepository.save(studentDTO.toModel());
        return student;
    }
    @Override
    public int deleteStudent(StudentDTO studentDTO){
        if(checkExist(studentDTO) == 0)  return 0;
        studentRepository.delete(studentDTO.toModel());
        return 1;
    }

    @Override
    public Page findBy(Pageable pageable, StudentDTO studentDTO) {
        return studentRepository.findAllByStudentId((org.springframework.data.domain.Pageable) pageable,studentDTO.getStudentId());
    }


}
