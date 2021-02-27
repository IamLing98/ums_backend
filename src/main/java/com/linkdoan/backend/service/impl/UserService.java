package com.linkdoan.backend.service.impl;

import com.linkdoan.backend.base.dto.CustomUserDetails;
import com.linkdoan.backend.dto.RoleDTO;
import com.linkdoan.backend.dto.UserDTO;
import com.linkdoan.backend.model.Employee;
import com.linkdoan.backend.model.Role;
import com.linkdoan.backend.model.Student;
import com.linkdoan.backend.model.User;
import com.linkdoan.backend.repository.EmployeeRepository;
import com.linkdoan.backend.repository.RoleRepository;
import com.linkdoan.backend.repository.StudentRepository;
import com.linkdoan.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service()
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    StudentRepository studentRepository;


    @Override
    public UserDetails loadUserByUsername(String username) {
        // Kiểm tra xem user có tồn tại trong database không?
        UserDTO userDTO = getUserDetails(username);
        return new CustomUserDetails(userDTO);
    }

    public UserDTO getUserDetails(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        UserDTO userDTO = user.toDto();
        List<Role> roleList = roleRepository.findAllRoles(user.getUserId());
        RoleDTO roleDTO = new RoleDTO();
        String[] employeeLabels;
        String[] studentLabels;
        if (roleList != null && !roleList.isEmpty()) {
            roleDTO = roleList.get(0).toDto();
        }
        userDTO.setRoleDTO(roleDTO);
        return userDTO;
    }

}