package com.linkdoan.backend.dto;

import com.linkdoan.backend.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class RoleDTO {
    private Long roleId;

    private String roleName;

    private String description;

    public Role toModel() {
        Role role = new Role();
        role.setRoleId(roleId);
        role.setRoleName(roleName);
        role.setDescription(description);
        return role;
    }
}
