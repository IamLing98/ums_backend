package com.linkdoan.backend.controller;

import com.linkdoan.backend.repository.RoleRepository;
import com.linkdoan.backend.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    UserService userService ;

    @Autowired
    RoleRepository roleRepository;

    @RequestMapping(value = "/users/getDetails", method = RequestMethod.GET)
    public ResponseEntity<?> getAll(@RequestParam(name = "username") String username) throws Exception {
       userService.getUserDetails(username);
        return new ResponseEntity<>( userService.getUserDetails(username), HttpStatus.OK);
    }


}