package com.linkdoan.backend.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class UnAuthorizedException extends RuntimeException {
    private String errorMessage;
}
