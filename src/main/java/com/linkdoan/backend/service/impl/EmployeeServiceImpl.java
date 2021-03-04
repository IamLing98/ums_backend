package com.linkdoan.backend.service.impl;

import com.linkdoan.backend.dto.EmployeeDTO;
import com.linkdoan.backend.model.*;
import com.linkdoan.backend.repository.EmployeeRepository;
import com.linkdoan.backend.repository.TeacherEducationTimelineRepository;
import com.linkdoan.backend.repository.TeacherSubjectRepository;
import com.linkdoan.backend.repository.TeacherWorkTimelineRepository;
import com.linkdoan.backend.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
        (
                propagation = Propagation.REQUIRED,
                readOnly = false,
                rollbackFor = Throwable.class
        )
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    TeacherWorkTimelineRepository teacherWorkTimelineRepository;

    @Autowired
    TeacherEducationTimelineRepository teacherEducationTimelineRepository;

    @Autowired
    TeacherSubjectRepository teacherSubjectRepository;

    @Override
    public EmployeeDTO getEmployeeDetail(String employeeId) {
        Optional<Employee> employeeOptional = employeeRepository.findById(employeeId);
        if (!employeeOptional.isPresent()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Không tồn tại!!!");
        List<EmployeeDTO> employeeDTOList = employeeRepository.getDetail(employeeId);
        if (employeeDTOList != null && !employeeDTOList.isEmpty()) {
            EmployeeDTO rs = employeeDTOList.get(0);
            if (rs != null) {
                rs.setTeacherEducationTimeLineList(employeeRepository.getListEducationTimeline(rs.getEmployeeId()));
                rs.setTeacherWorkTimeLineList(employeeRepository.getListWorkTimeline(rs.getEmployeeId()));
                rs.setSubjectList(employeeRepository.getListSubject(rs.getEmployeeId()));
            }
            return rs;
        } else return null;
    }

    @Override
    public List<EmployeeDTO> getAllEmployee(Long type) {
        List<EmployeeDTO> rs = new ArrayList<>();
        if (type != null && type == 1L) {
            rs = employeeRepository.findAllTeacher();
        } else {
            rs = employeeRepository.findAllEmployee();
        }

        return rs;
    }

    @Override
    public int create(EmployeeDTO employeeDTO) {
        System.out.println(employeeDTO.getFullName());
        Employee employee = employeeRepository.save(employeeDTO.toModel());
        String employeeId = employee.getEmployeeId();
        if (employeeDTO.getSubjectList() != null) {
            List<Subject> subjectList = employeeDTO.getSubjectList();
            List<TeacherSubject> teacherSubjectList = subjectList.stream().map(subject -> {
                TeacherSubject teacherSubject = new TeacherSubject();
                teacherSubject.setTeacherId(employeeId);
                teacherSubject.setSubjectId(subject.getSubjectId());
                return teacherSubject;
            }).collect(Collectors.toCollection(ArrayList<TeacherSubject>::new));
            teacherSubjectRepository.saveAll(teacherSubjectList);
        }
        if (employeeDTO.getTeacherWorkTimeLineList() != null) {
            List<TeacherWorkTimeLine> teacherWorkTimeLines = employeeDTO.getTeacherWorkTimeLineList().stream().map(teacherWorkTimeLine -> {
                teacherWorkTimeLine.setTeacherId(employeeId);
                return teacherWorkTimeLine;
            }).collect(Collectors.toCollection(ArrayList<TeacherWorkTimeLine>::new));
            teacherWorkTimelineRepository.saveAll(teacherWorkTimeLines);
        }
        if (employeeDTO.getTeacherEducationTimeLineList() != null) {
            List<TeacherEducationTimeLine> teacherEducationTimeLines = employeeDTO.getTeacherEducationTimeLineList().stream().map(teacherEducationTimeLine -> {
                teacherEducationTimeLine.setTeacherId(employeeId);
                return teacherEducationTimeLine;
            }).collect(Collectors.toCollection(ArrayList<TeacherEducationTimeLine>::new));
            teacherEducationTimelineRepository.saveAll(teacherEducationTimeLines);
        }
        return 1;
    }

    @Override
    public int updateEmployee(String employeeId, EmployeeDTO employeeDTO) {
        Optional<Employee> employeeOptional = employeeRepository.findById(employeeId);
        if (!employeeOptional.isPresent()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Không tồn tại!!!");
        teacherEducationTimelineRepository.deleteAllByTeacherId(employeeId);
        teacherWorkTimelineRepository.deleteAllByTeacherId(employeeId);
        teacherSubjectRepository.deleteAllByTeacherId(employeeId);
        employeeDTO.setEmployeeId(employeeId);
        employeeRepository.save(employeeDTO.toModel());
        if (employeeDTO.getSubjectList() != null) {
            List<Subject> subjectList = employeeDTO.getSubjectList();
            List<TeacherSubject> teacherSubjectList = subjectList.stream().map(subject -> {
                TeacherSubject teacherSubject = new TeacherSubject();
                teacherSubject.setTeacherId(employeeId);
                teacherSubject.setSubjectId(subject.getSubjectId());
                return teacherSubject;
            }).collect(Collectors.toCollection(ArrayList<TeacherSubject>::new));
            teacherSubjectRepository.saveAll(teacherSubjectList);
        }
        if (employeeDTO.getTeacherWorkTimeLineList() != null) {
            List<TeacherWorkTimeLine> teacherWorkTimeLines = employeeDTO.getTeacherWorkTimeLineList().stream().map(teacherWorkTimeLine -> {
                teacherWorkTimeLine.setTeacherId(employeeId);
                return teacherWorkTimeLine;
            }).collect(Collectors.toCollection(ArrayList<TeacherWorkTimeLine>::new));
            teacherWorkTimelineRepository.saveAll(teacherWorkTimeLines);
        }
        if (employeeDTO.getTeacherEducationTimeLineList() != null) {
            List<TeacherEducationTimeLine> teacherEducationTimeLines = employeeDTO.getTeacherEducationTimeLineList().stream().map(teacherEducationTimeLine -> {
                teacherEducationTimeLine.setTeacherId(employeeId);
                return teacherEducationTimeLine;
            }).collect(Collectors.toCollection(ArrayList<TeacherEducationTimeLine>::new));
            teacherEducationTimelineRepository.saveAll(teacherEducationTimeLines);
        }
        return 1;
    }

    @Override
    public int deleteEmployee(String employeeId) {
        Optional<Employee> employeeOptional = employeeRepository.findById(employeeId);
        if (!employeeOptional.isPresent()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Không tồn tại!!!");
        teacherEducationTimelineRepository.deleteAllByTeacherId(employeeId);
        teacherWorkTimelineRepository.deleteAllByTeacherId(employeeId);
        teacherSubjectRepository.deleteAllByTeacherId(employeeId);
        employeeRepository.deleteById(employeeId);
        return 1;
    }
}
