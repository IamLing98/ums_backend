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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.io.IOException;
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

    private ClassRepository classRepository;
    public ClassRepository getClassRepository(){
        return this.classRepository;
    }
     public void setStudentRepository(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    @Override
    public Student insertStudent(StudentDTO studentDTO)throws IOException {
        String classId = studentDTO.getClassId();
        String departmentId = studentDTO.getDepartmentId();
        Pageable pageable = PageRequest.of(0, 1);
        String studentId = "";
        Page<Class> classes = classRepository.findBy(classId, departmentId, pageable);
        if(classes.getTotalElements() < 1) throw  new EntityNotFoundException("Lớp học không hợp lệ !!!");
        Optional<Integer> nextValOptional = classRepository.findNextValueSeqInClass(classId);
        if(!nextValOptional.isPresent()) {
            Integer subDepartmentId = Integer.parseInt(departmentId.substring(4));
            Integer subClassId = Integer.parseInt(classId.substring(3));
            Integer nextVal = nextValOptional.get();
            studentId = subDepartmentId.toString() + subClassId.toString() + nextVal.toString();
        }
        else throw  new EntityNotFoundException("Không hợp lệ");
        studentDTO.setStudentId(studentId);
        return studentRepository.save(studentDTO.toModel());
    }

    @Override
    public Student updateStudent(StudentDTO studentDTO) throws IOException {
        if(!studentRepository.existsById(studentDTO.getStudentId() ) ) throw  new EntityExistsException("Sinh viên này chưa có trên hệ thống");
        Class classModel = classRepository.findFirstByClassId(studentDTO.getClassId());
        Department department = departmentRepository.findFirstByDepartmentId(studentDTO.getDepartmentId());
        return studentRepository.save(studentDTO.toModel( ));
    }
    @Override
    public int deleteStudent(StudentDTO studentDTO)throws IOException{
        if(!studentRepository.existsById(studentDTO.getStudentId() ) ) throw  new EntityExistsException("Sinh viên này chưa có trên hệ thống");
        studentRepository.delete(studentDTO.toModel(  ));
        return 1;
    }

    @Override
    public Page findBy( StudentDTO studentDTO) throws IOException{
        Pageable pageable = PageRequest.of(studentDTO.getPage(), studentDTO.getPageSize());
        return studentRepository.findBy(studentDTO.getKeySearch1(), studentDTO.getKeySearch2(),studentDTO.getKeySearch3(),studentDTO.getKeySearch4(),studentDTO.getKeySearch5() ,pageable);
    }


}
