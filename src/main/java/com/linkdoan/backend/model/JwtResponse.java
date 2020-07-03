package com.linkdoan.backend.model;

import com.linkdoan.backend.dto.UserDTO;

import java.io.Serializable;
public class JwtResponse implements Serializable {
    private static final long serialVersionUID = -8091879091924046844L;
    private final String jwttoken;
    private UserDTO userDTO;
     public JwtResponse(String jwttoken) {
        this.jwttoken = jwttoken;
    }

    public JwtResponse(String jwttoken, UserDTO userDTO) {
        this.jwttoken = jwttoken;
        this.userDTO = userDTO;
    }

    public String getToken() {
        return this.jwttoken;
    }
}