package com.linkdoan.backend.service.impl;

import com.linkdoan.backend.dto.StudentInvoiceDTO;
import com.linkdoan.backend.model.*;
import com.linkdoan.backend.repository.InvoiceCategoryRepository;
import com.linkdoan.backend.repository.StudentFeeTuitionRepository;
import com.linkdoan.backend.repository.StudentInvoiceRepository;
import com.linkdoan.backend.repository.TermRepository;
import com.linkdoan.backend.service.StudentInvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.*;

@Service
@Transactional
        (
                propagation = Propagation.REQUIRED,
                readOnly = false,
                rollbackFor = Throwable.class
        )
public class StudentInvoiceServiceImpl implements StudentInvoiceService {

    @Autowired
    StudentInvoiceRepository studentInvoiceRepository;

    @Autowired
    InvoiceCategoryRepository invoiceCategoryRepository;

    @Autowired
    StudentFeeTuitionRepository studentFeeTuitionRepository;

    @Autowired
    TermRepository termRepository;

    @Override
    public List<Map<String, Object>> getAll(Integer type, String termId) {
        System.out.println("termId: " + termId);
        String[] labels = {"amount", "creatorId", "invoiceCreatedDate", "invoiceName", "invoiceNo", "invoiceType", "reasonId", "studentABN", "termId", "fullName", "reasonName", "note"};
        List<Map<String, Object>> rs = new ArrayList<>();
        List<Object[]> studentInvoicesObjectArrayList = studentInvoiceRepository.getAllStudentInvoiceByType(type, termId);
        if (studentInvoicesObjectArrayList != null && !studentInvoicesObjectArrayList.isEmpty()) {
            for (Object[] studentInvoicesObjectArray : studentInvoicesObjectArrayList) {
                Map<String, Object> studentInvoicesMap = new HashMap<>();
                for (int i = 0; i < labels.length; i++) {
                    studentInvoicesMap.put(labels[i], studentInvoicesObjectArray[i]);
                }
                rs.add(studentInvoicesMap);
            }
        }
        return rs;

    }

    @Override
    public int createNewStudentInvoice(StudentInvoiceDTO studentInvoiceDTO, String username) {
        Student student = studentInvoiceDTO.getStudent();
        Term term = studentInvoiceDTO.getTerm();
        String studentId = student.getStudentId();
        String termId = term.getId();
        Optional<Term> termOptinal = termRepository.findById(termId);
        if (!termOptinal.isPresent()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Học kỳ hông hợp lệ!!!");
        term = termOptinal.get();
        LocalDateTime localDateTime = LocalDateTime.now();
        Invoice invoice = new Invoice();
        invoice.setStudentABN(studentId);
        invoice.setAmount(studentInvoiceDTO.getAmount());
        invoice.setCreatorId(username);
        invoice.setReasonId(studentInvoiceDTO.getReasonId());
        invoice.setInvoiceCreatedDate(localDateTime);
        invoice.setInvoiceType(studentInvoiceDTO.getInvoiceType());
        invoice.setTermId(termId);
        invoice.setNote(studentInvoiceDTO.getNote());
        if (invoice.getReasonId() == 1) {
            invoice.setInvoiceName("Biên lai thu tiền");
        }
        Invoice savedInvoice = studentInvoiceRepository.save(invoice);
        List<FeeCategory> feeCategoryList = studentInvoiceDTO.getFeeCategories();
        List<InvoiceCategory> invoiceCategories = new ArrayList<>();
        List<StudentsFeeCategories> studentsFeeCategoriesList = new ArrayList<>();
        for (FeeCategory feeCategory : feeCategoryList) {
            Optional<StudentsFeeCategories> studentsFeeCategoriesOptional = studentFeeTuitionRepository.findFirstByStudentIdAndTermIdAndFeeCategoriesId(studentId, termId, feeCategory.getId());
            if (studentsFeeCategoriesOptional.isPresent()) {
                StudentsFeeCategories studentsFeeCategories = studentsFeeCategoriesOptional.get();
                studentsFeeCategories.setIsPaid(1);
                studentsFeeCategories.setTransactionDate(localDateTime);
                studentsFeeCategoriesList.add(studentsFeeCategories);
            } else throw new ResponseStatusException(HttpStatus.CONFLICT, "Hoá đơn không hợp lệ!!!");
            InvoiceCategory invoiceCategory = new InvoiceCategory();
            invoiceCategory.setCategoryId(feeCategory.getId());
            invoiceCategory.setInvoiceNo(savedInvoice.getInvoiceNo());
            invoiceCategories.add(invoiceCategory);
        }
        if (savedInvoice.getInvoiceType() == 0) {
            term.setInFeeTotalValue(term.getInFeeTotalValue() + savedInvoice.getAmount().intValue());
        } else if (savedInvoice.getInvoiceType() == 1) {
            term.setOutFeeTotalValue(term.getOutFeeTotalValue() - savedInvoice.getAmount().intValue());
        }
        studentFeeTuitionRepository.saveAll(studentsFeeCategoriesList);
        invoiceCategoryRepository.saveAll(invoiceCategories);
        return 1;
    }

    @Override
    public int updateStudentInvoice(StudentInvoiceDTO studentInvoiceDTO) {
        return 0;
    }

    @Override
    public int deleteStudentInvoice(List<Long> ids) {
        return 0;
    }

    @Override
    public StudentInvoiceDTO getDetail(Long studentInvoiceId) {
        return null;
    }


}
