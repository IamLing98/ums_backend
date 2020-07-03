package com.linkdoan.backend.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author anhnv174
 **/
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BadRequestException extends RuntimeException {
    private String errorMessage;
}
