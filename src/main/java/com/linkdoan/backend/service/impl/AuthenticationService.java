package com.linkdoan.backend.service.impl;

import com.linkdoan.backend.dto.SysUserDTO;

public interface AuthenticationService {
    SysUserDTO getCurrentUser();
}
