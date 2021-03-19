package com.linkdoan.backend.service.impl;

import com.linkdoan.backend.base.dto.CustomUserDetails;
import com.linkdoan.backend.dto.RoleDTO;
import com.linkdoan.backend.dto.UserDTO;
import com.linkdoan.backend.model.Role;
import com.linkdoan.backend.model.User;
import com.linkdoan.backend.repository.EmployeeRepository;
import com.linkdoan.backend.repository.RoleRepository;
import com.linkdoan.backend.repository.StudentRepository;
import com.linkdoan.backend.repository.UserRepository;
import com.linkdoan.backend.service.UserSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UserService implements UserDetailsService, UserSevice {

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
        List<Role> roleList = roleRepository.findAllRoles(user.getUsername());
        RoleDTO roleDTO = new RoleDTO();
        String[] employeeLabels;
        String[] studentLabels;
        if (roleList != null && !roleList.isEmpty()) {
            roleDTO = roleList.get(0).toDto();
        }
        userDTO.setRoleDTO(roleDTO);
        return userDTO;
    }

    @Override
    public UserDTO changePassword(UserDTO userDTO, String username) {
        org.springframework.security.crypto.password.PasswordEncoder encoder
                = new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder();
        if (!userDTO.getUsername().equals(username))
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Lỗi xác thực");
        User user = userRepository.findByUsername(userDTO.getUsername());
        System.out.println(encoder.matches(userDTO.getPassword(), user.getPassword()));
        if (!encoder.matches(userDTO.getPassword(), user.getPassword()))
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Mật khẩu không đúng");
        if (user != null) {
            user.setPassword(encoder.encode(userDTO.getNewPassword()));
            userRepository.save(user);
            return user.toDto();
        } else throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found");
    }
}