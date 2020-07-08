package com.linkdoan.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class UploadFileResponse implements Serializable {
    private String fileName;
    private String fileDownloadUri;
    private String fileType;
    private long size;



    // Getters and Setters (Omitted for brevity)
}