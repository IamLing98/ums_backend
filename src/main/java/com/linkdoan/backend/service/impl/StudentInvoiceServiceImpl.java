package com.linkdoan.backend.service.impl;

import com.linkdoan.backend.dto.FeeCategoryDTO;
import com.linkdoan.backend.dto.StudentInvoiceDTO;
import com.linkdoan.backend.model.*;
import com.linkdoan.backend.repository.*;
import com.linkdoan.backend.service.StudentInvoiceService;
import com.linkdoan.backend.util.MoneyUtil;
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
    InvoiceRepository invoiceRepository;

    @Autowired
    InvoiceItemRepository invoiceItemRepository;

    @Autowired
    StudentFeeTuitionRepository studentFeeTuitionRepository;

    @Autowired
    TermRepository termRepository;

    @Autowired
    StudentRepository studentRepository;


    @Override
    public List<Map<String, Object>> getAll(Integer type, String termId) {
        System.out.println("termId: " + termId);
        String[] labels = {"amount", "creatorId", "invoiceCreatedDate", "invoiceName", "invoiceNo", "invoiceType", "reasonId", "studentABN", "termId", "fullName", "reasonName", "note"};
        List<Map<String, Object>> rs = new ArrayList<>();
        List<Object[]> studentInvoicesObjectArrayList = invoiceRepository.getAllStudentInvoiceByType(type, termId);
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
        if (invoice.getInvoiceType() == 1) {
            invoice.setInvoiceName("Phiếu thu");
        }
        if (invoice.getInvoiceType() == 1) {
            invoice.setInvoiceName("Phiếu  chi");
        }
        Invoice savedInvoice = invoiceRepository.save(invoice);
        List<StudentsFeeCategory> studentsFeeCategories = studentInvoiceDTO.getStudentsFeeCategories();
        List<InvoiceItem> invoiceCategories = new ArrayList<>();
        List<StudentsFeeCategory> studentsFeeCategoryList = new ArrayList<>();
        for (StudentsFeeCategory studentsFeeCategory : studentsFeeCategories) {
            Optional<StudentsFeeCategory> studentsFeeCategoriesOptional = studentFeeTuitionRepository.findById(studentsFeeCategory.getId());
            if (studentsFeeCategoriesOptional.isPresent()) {
                StudentsFeeCategory studentsFeeCategoryChange = studentsFeeCategoriesOptional.get();
                studentsFeeCategoryChange.setIsPaid(1);
                studentsFeeCategoryChange.setTransactionDate(localDateTime);
                studentsFeeCategoryList.add(studentsFeeCategoryChange);
            } else throw new ResponseStatusException(HttpStatus.CONFLICT, "Hoá đơn không hợp lệ!!!");
            InvoiceItem invoiceItem = new InvoiceItem();
            invoiceItem.setStudentCategoryId(studentsFeeCategory.getId());
            invoiceItem.setInvoiceNo(savedInvoice.getInvoiceNo());
            invoiceCategories.add(invoiceItem);
        }
        if (savedInvoice.getInvoiceType() == 0) {
            term.setInFeeTotalValue(term.getInFeeTotalValue() + savedInvoice.getAmount().intValue());
        } else if (savedInvoice.getInvoiceType() == 1) {
            term.setOutFeeTotalValue(term.getOutFeeTotalValue() - savedInvoice.getAmount().intValue());
        }
        studentFeeTuitionRepository.saveAll(studentsFeeCategoryList);
        invoiceItemRepository.saveAll(invoiceCategories);
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
    //get invoice detail
    public StudentInvoiceDTO getDetail(Long studentInvoiceId) {
        StudentInvoiceDTO studentInvoiceDTO = new StudentInvoiceDTO();
        Optional<Invoice> invoiceOptional = invoiceRepository.findById(studentInvoiceId);
        if (!invoiceOptional.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Hoá đơn không tồn tại");
        Invoice invoice = invoiceOptional.get();
        Optional<Term> termOptional = termRepository.findById(invoice.getTermId());
        if (!termOptional.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Hoá đơn không hợp lệ. Học kỳ không đúng!!!");
        Term term = termOptional.get();
        Optional<Student> studentOptional = studentRepository.findById(invoice.getStudentABN());
        if (!studentOptional.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Hoá đơn không hợp lệ. Sinh viên không tồn tại");
        Student student = studentOptional.get();
        List<FeeCategoryDTO> studentFeeCategories = invoiceItemRepository.getListInvoiceItem(studentInvoiceId);
        studentInvoiceDTO.setStudent(student);
        studentInvoiceDTO.setItems(studentFeeCategories);
        studentInvoiceDTO.setInvoiceNo(invoice.getInvoiceNo());
        studentInvoiceDTO.setAmount(invoice.getAmount());
        MoneyUtil moneyUtil = new MoneyUtil();
        studentInvoiceDTO.setTextMoney(moneyUtil.readNum(String.valueOf(invoice.getAmount().intValue())));
        studentInvoiceDTO.setCreatorId(invoice.getCreatorId());
        studentInvoiceDTO.setInvoiceName(invoice.getInvoiceName());
        studentInvoiceDTO.setReasonId(invoice.getReasonId());
        studentInvoiceDTO.setTerm(term);
        studentInvoiceDTO.setInvoiceType(invoice.getInvoiceType());
        studentInvoiceDTO.setInvoiceCreatedDate(invoice.getInvoiceCreatedDate());
        return studentInvoiceDTO;
    }


}
