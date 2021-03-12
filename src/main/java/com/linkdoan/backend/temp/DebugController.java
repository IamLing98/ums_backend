package com.linkdoan.backend.temp;

import com.linkdoan.backend.model.Student;
import com.linkdoan.backend.model.StudentSubject;
import com.linkdoan.backend.model.Subject;
import com.linkdoan.backend.repository.StudentRepository;
import com.linkdoan.backend.repository.StudentSubjectRepository;
import com.linkdoan.backend.repository.SubjectClassRepository;
import com.linkdoan.backend.repository.SubjectRepository;
import com.linkdoan.backend.service.TuitionFeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class DebugController {

    @Autowired
    TuitionFeeService tuitionFeeService;

    @Autowired
    SubjectClassRepository scheduleService;

    @Autowired
    StudentSubjectRepository studentSubjectRepository;

    @Qualifier("subjectRepository")
    @Autowired
    SubjectRepository subjectRepository;

    @Autowired
    StudentRepository studentRepository;

    @GetMapping(value = "/debug")
    public ResponseEntity<?> debug() {
        List<Student> studentList = studentRepository.findAll();
        List<StudentSubject> studentSubjectList = new ArrayList<>();
        studentList.stream().forEach(student -> {
            String educationProgramId = student.getEducationProgramId();
            List<Subject> subjectList = subjectRepository.findAllByEducationProgramId(educationProgramId);
            subjectList.stream().forEach(subject -> {
                StudentSubject studentSubject = new StudentSubject();
                studentSubject.setSubjectId(subject.getSubjectId());
                studentSubject.setEducationProgramId(educationProgramId);
                studentSubject.setStudentId(student.getStudentId());
                studentSubjectList.add(studentSubject);
            });
        });
        studentSubjectRepository.saveAll(studentSubjectList);

        return new ResponseEntity<>(studentSubjectList, HttpStatus.OK);
    }
}
