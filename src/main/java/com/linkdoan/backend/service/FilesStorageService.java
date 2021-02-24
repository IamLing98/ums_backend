package com.linkdoan.backend.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FilesStorageService {

    String storeFile(MultipartFile file);

    Resource loadFileAsResource(String fileName);

    void deleteFileAsResource(String fileName);
}
