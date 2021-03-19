package com.linkdoan.backend.service.impl;

import com.linkdoan.backend.base.dto.CustomUserDetails;
import com.linkdoan.backend.dto.RoleDTO;
import com.linkdoan.backend.dto.UserDTO;
import com.linkdoan.backend.model.Role;
import com.linkdoan.backend.model.Student;
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

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

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
    public UserDTO update(UserDTO userDTO, String username, String actionType) {
        org.springframework.security.crypto.password.PasswordEncoder encoder
                = new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder();
        LocalDateTime localDateTime = LocalDateTime.now();
        if (actionType.equals("RESTORE")) {
            Optional<User> userOptional = userRepository.findById(userDTO.getUsername());
            if (userOptional.isPresent()) {
                String password = "123456";
                String encodePassword = encoder.encode(password);
                User user = userOptional.get();
                user.setPassword(encodePassword);
                user.setUpdatedAt(localDateTime);
                return userRepository.save(user).toDto();
            }
            return null;
        } else if (actionType.equals("CHANGE")) {
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
        } else throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Action khong hop le");
    }

    @Override
    public List<UserDTO> getAllUser(String role) {
        List<UserDTO> userList = userRepository.getAllByRoleName(role);
        return userList;
    }

    @Override
    public List<Student> getAllStudentNotHasAccount() {
        List<Student> studentHasAccount = userRepository.getAllStudentHasAccount();
        System.out.println(studentHasAccount);
        if (studentHasAccount != null) {
            List<String> studentIdList = studentHasAccount.stream().map(student -> student.getStudentId()).collect(Collectors.toList());
            System.out.println(studentIdList);
            List<Student> notHasAccountStudentList = userRepository.getAllNotHasAccountStudentList(studentIdList);
            return notHasAccountStudentList;
        } else return studentRepository.findAll();
    }

    @Override
    public List<UserDTO> create(List<UserDTO> userDTOS, String role) throws ExecutionException, InterruptedException {
        org.springframework.security.crypto.password.PasswordEncoder encoder
                = new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder();
        LocalDateTime localDateTime = LocalDateTime.now();
        if (role.equals("SV")) {
            List<User> userList = userDTOS.parallelStream().map(userDTO -> {
                Optional<User> userOptional = userRepository.findById(userDTO.getUsername());
                Optional<Student> studentOptional = studentRepository.findById(userDTO.getUsername());
                if (!userOptional.isPresent() && studentOptional.isPresent()) {
                    User user = new User();
                    Student student = studentOptional.get();
                    if (student.getDateBirth() != null) {
                        String password = student.getDateBirth().format(DateTimeFormatter.ofPattern("ddMMyyyy"));
                        String encodePassword = encoder.encode(password);
                        user.setPassword(encodePassword);
                        user.setCreatedAt(localDateTime);
                        user.setIsActive(1);
                        user.setIsEmailVerified(1);
                        user.setEmail(student.getEmail());
                        user.setUsername(userDTO.getUsername());
                        user.setRoleId(2L);
                        return user;
                    } else return null;
                }
                return null;
            }).filter(user -> user != null).collect(Collectors.toList());
            userRepository.saveAll(userList);
        }
        return null;
    }

    @Override
    public int delete(String username) {
        Optional<User> userOptional = userRepository.findById(username);
        if (!userOptional.isPresent()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Khong ton tai");
        User user = userOptional.get();
        userRepository.delete(user);
        return 1;
    }
}