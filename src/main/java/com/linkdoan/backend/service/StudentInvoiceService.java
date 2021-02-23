package com.linkdoan.backend.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

public interface StudentInvoiceService {

    List<Map<String, Object>> getAll(Integer type);
}
