package com.linkdoan.backend.controller;

import com.linkdoan.backend.dto.UserDTO;
import com.linkdoan.backend.repository.RoleRepository;
import com.linkdoan.backend.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    RoleRepository roleRepository;

    @RequestMapping(value = "/users/getDetails", method = RequestMethod.GET)
    public ResponseEntity<?> getAll(@RequestParam(name = "username") String username) throws Exception {
        return new ResponseEntity<>(userService.getUserDetails(username), HttpStatus.OK);
    }

    @PutMapping(value="/users")
    public ResponseEntity<?> changePassword(@RequestBody UserDTO userDTO, SecurityContextHolder securityContextHolder) throws Exception {
        String username = securityContextHolder.getContext().getAuthentication().getName();
        return new ResponseEntity<>(userService.changePassword(userDTO,username), HttpStatus.OK);
    }

}