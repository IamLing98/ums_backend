package com.linkdoan.backend.service;

import org.springframework.core.io.Resource;

import java.util.HashMap;

public interface DocumentGenerationService {
    String generateFile(HashMap<String, String> stringStringMap, Long id) throws Exception;
}
