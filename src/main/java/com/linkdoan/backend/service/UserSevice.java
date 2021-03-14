package com.linkdoan.backend.service;

import com.linkdoan.backend.dto.UserDTO;

public interface UserSevice {

    UserDTO changePassword(UserDTO userDTO, String username);
}
