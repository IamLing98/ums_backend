package com.linkdoan.backend.service.impl;

import com.linkdoan.backend.base.dto.CustomUserDetails;
import com.linkdoan.backend.dto.RoleDTO;
import com.linkdoan.backend.dto.UserDTO;
import com.linkdoan.backend.model.Role;
import com.linkdoan.backend.model.User;
import com.linkdoan.backend.repository.RoleRepository;
import com.linkdoan.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service()
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        // Kiểm tra xem user có tồn tại trong database không?
        UserDTO userDTO = getUserDetails(username);
        return new CustomUserDetails(userDTO);
    }

    public UserDTO getUserDetails(String username){
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        UserDTO userDTO = user.toDto();
        List<Role> roleList = roleRepository.findAllRoles(user.getUserId());
        List<RoleDTO> roleDTOList = new ArrayList<>();
        for(int i = 0 ; i< roleList.size(); i++){
            RoleDTO roleDTO = roleList.get(i).toDto();
            roleDTOList.add(roleDTO);
        }
        userDTO.setRoles(roleDTOList);
        return userDTO;
    }

}