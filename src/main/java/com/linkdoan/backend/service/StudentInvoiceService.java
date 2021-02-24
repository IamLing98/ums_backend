package com.linkdoan.backend.service;

import com.linkdoan.backend.dto.StudentInvoiceDTO;
import com.linkdoan.backend.model.Student;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

public interface StudentInvoiceService {

    List<Map<String, Object>> getAll(Integer type, String termId);

    int createNewStudentInvoice(StudentInvoiceDTO studentInvoiceDTO, String username);

    int updateStudentInvoice(StudentInvoiceDTO studentInvoiceDTO);

    int deleteStudentInvoice(List<Long> ids);

    StudentInvoiceDTO getDetail(Long studentInvoiceId );
}
