package com.linkdoan.backend.dto;

import com.linkdoan.backend.base.anotation.AdjHistory;
import com.linkdoan.backend.model.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRoleDTO {
    private Long roleId;

    @AdjHistory(field = "userId")
    private Long userId;

    private String roleName;

    private String description;

    public UserRole toModel() {
        UserRole userRole = new UserRole();
//        userRole.setRoleId(roleId);
//        userRole.setUserId(userId);
        return userRole;
    }
}