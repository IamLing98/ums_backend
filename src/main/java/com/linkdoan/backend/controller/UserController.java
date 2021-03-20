package com.linkdoan.backend.controller;

import com.linkdoan.backend.dto.UserDTO;
import com.linkdoan.backend.repository.RoleRepository;
import com.linkdoan.backend.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    RoleRepository roleRepository;

    @RequestMapping(value = "/users/getDetails", method = RequestMethod.GET)
    public ResponseEntity<?> getDetail(@RequestParam(name = "username") String username) throws Exception {
        return new ResponseEntity<>(userService.getUserDetails(username), HttpStatus.OK);
    }

    @GetMapping(value = "/users")
    public ResponseEntity<?> getAll(@RequestParam(value = "role") String role) throws Exception {
        return new ResponseEntity<>(userService.getAllUser(role), HttpStatus.OK);
    }

    @GetMapping(value = "/users/students")
    public ResponseEntity<?> getAllStudents() throws Exception {
        return new ResponseEntity<>(userService.getAllStudentNotHasAccount(), HttpStatus.OK);
    }

    @GetMapping(value = "/users/teachers")
    public ResponseEntity<?> getAllTeachers() throws Exception {
        return new ResponseEntity<>(userService.getAllTeacherHasNoAccount(), HttpStatus.OK);
    }

    @PostMapping(value = "/users/create")
    public ResponseEntity<?> create(@RequestParam(value = "role") String role, @RequestBody List<UserDTO> userDTOS) throws Exception {
        System.out.println("userr DTOS Lít: " + userDTOS);
        return new ResponseEntity<>(userService.create(userDTOS, role), HttpStatus.OK);
    }

    @PutMapping(value = "/users")
    public ResponseEntity<?> update(@RequestParam(value = "actionType") String actionType,
                                    @RequestBody UserDTO userDTO,
                                    SecurityContextHolder securityContextHolder) throws Exception {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return new ResponseEntity<>(userService.update(userDTO, username, actionType), HttpStatus.OK);
    }

    @DeleteMapping(value = "/users/{username}")
    public ResponseEntity<?> delete(SecurityContextHolder securityContextHolder, @PathVariable("username") String username) throws Exception {
//        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return new ResponseEntity<>(userService.delete(username), HttpStatus.OK);
    }

}