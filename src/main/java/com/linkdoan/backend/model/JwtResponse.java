package com.linkdoan.backend.model;


import com.linkdoan.backend.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class JwtResponse implements Serializable {
    private static final long serialVersionUID = -8091879091924046844L;
    private final String jwttoken;
    private UserDTO user;


    public String getToken() {
        return this.jwttoken;
    }
}