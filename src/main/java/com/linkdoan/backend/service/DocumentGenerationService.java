package com.linkdoan.backend.service;

import java.util.HashMap;

public interface DocumentGenerationService {

    String generatePdfFile(HashMap<String, String> stringStringMap, Long id) throws Exception;

    String generateExcelFile(HashMap<String, Object> variables, Long id) throws Exception;
}
