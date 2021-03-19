package com.linkdoan.backend.service;

import com.linkdoan.backend.dto.UserDTO;
import com.linkdoan.backend.model.Student;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface UserSevice {

    UserDTO update(UserDTO userDTO, String username, String actionType);

    List<UserDTO> getAllUser(String role);

    List<Student> getAllStudentNotHasAccount();

    List<UserDTO> create(List<UserDTO> userDTOS, String role) throws ExecutionException, InterruptedException;

    int delete(String username);
}
