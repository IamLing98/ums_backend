package com.linkdoan.backend.service.impl;

import com.linkdoan.backend.dto.StudentDTO;
import com.linkdoan.backend.model.Class;
import com.linkdoan.backend.model.Department;
import com.linkdoan.backend.model.Student;
import com.linkdoan.backend.repository.ClassRepository;
import com.linkdoan.backend.repository.DepartmentRepository;
import com.linkdoan.backend.repository.StudentRepository;
import com.linkdoan.backend.service.StudentService;
import com.linkdoan.backend.util.SystemConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.EntityExistsException;
import java.io.IOException;

@Service("studentService")
public class StudentServiceImpl implements StudentService  {

    private static final String STUDENT = "Student";

    @Qualifier("studentRepository")
    @Autowired
    private StudentRepository studentRepository;

    @Qualifier("departmentRepository")
    @Autowired
    private DepartmentRepository departmentRepository;

    private ClassRepository classRepository;
    public ClassRepository getClassRepository(){
        return this.classRepository;
    }
     public void setStudentRepository(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }


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
    public Page<Student> getListStudent( Pageable pageable)throws IOException {
          return studentRepository.findAll( pageable);
    }

    @Override
    public Student insertStudent(StudentDTO studentDTO)throws IOException {
        if(checkExist(studentDTO) == 1) throw  new EntityExistsException("Sinh viên này đã tồn tại");
        Class classModel = classRepository.findClassByClassId(studentDTO.getClassId());
        Department department = departmentRepository.findFirstByDepartmentId(studentDTO.getDepartmentId());
        return studentRepository.save(studentDTO.toModel( ));
    }

    @Override
    public Student updateStudent(StudentDTO studentDTO) throws IOException {
        if(checkExist(studentDTO) == 0)  throw  new EntityExistsException("Sinh viên này chưa có trên hệ thống");
        Class classModel = classRepository.findClassByClassId(studentDTO.getClassId());
        Department department = departmentRepository.findFirstByDepartmentId(studentDTO.getDepartmentId());
        return studentRepository.save(studentDTO.toModel( ));
    }
    @Override
    public int deleteStudent(StudentDTO studentDTO)throws IOException{
        if(checkExist(studentDTO) == 0)  return 0;
        Class classModel = classRepository.findClassByClassId(studentDTO.getClassId());
        Department department = departmentRepository.findFirstByDepartmentId(studentDTO.getDepartmentId());
        studentRepository.delete(studentDTO.toModel(  ));
        return 1;
    }

    @Override
    public Page findBy(Pageable pageable, StudentDTO studentDTO) throws IOException{
        return studentRepository.findByLastName(studentDTO.getKeySearch1(), studentDTO.getKeySearch2(),studentDTO.getKeySearch3(),studentDTO.getKeySearch4(),studentDTO.getKeySearch5() ,pageable);
    }


}
