package com.linkdoan.backend.service.impl;

import com.linkdoan.backend.base.dto.DataListDTO;
import com.linkdoan.backend.base.dto.SearchDTO;
import com.linkdoan.backend.dto.StudentsDTO;
import com.linkdoan.backend.dto.UserDTO;
import com.linkdoan.backend.repository.StudentRepository;

import java.util.List;

public class StudentService {
    StudentRepository studentRepository;
    public StudentsDTO addStudent(StudentsDTO obj){
        StudentsDTO studentsDTO = new StudentsDTO();
        return  studentsDTO;
    }
    public DataListDTO doSearch(SearchDTO obj) {
        List<StudentsDTO> ls = studentRepository.doSearch(obj);
        DataListDTO data = new DataListDTO();
        data.setData(ls);
        data.setTotal(obj.getTotalRecord());
        data.setSize(obj.getTotalRecord());
        data.setStart(1);
        return data;
    }
}
