package com.linkdoan.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileDTO {
    private String action;
    private String document;
    private String elementId;
    private Boolean isFileName;
    private String uniqueId;
    private String zoomFactor;
}
