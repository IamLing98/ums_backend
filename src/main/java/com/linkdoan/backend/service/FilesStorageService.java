package com.linkdoan.backend.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;

public interface FilesStorageService {

    String storeFile(MultipartFile file);

    Resource loadFileAsResource(String fileName);

    void deleteFileAsResource(String fileName);

    Path getPathFile(String fileName);

    String getPostfix(String path);
}
