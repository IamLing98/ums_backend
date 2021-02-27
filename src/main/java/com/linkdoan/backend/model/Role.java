package com.linkdoan.backend.model;

import com.linkdoan.backend.dto.RoleDTO;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "role")
@Data // lombok
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id", unique = true)
    private Long roleId;

    @Column(name = "role_name")
    private String roleName;

    @Column(name = "description")
    private String description;

    public RoleDTO toDto() {
        RoleDTO sysRole = new RoleDTO();
        sysRole.setRoleId(roleId);
        sysRole.setRoleName(roleName);
        sysRole.setDescription(description);
        return sysRole;
    }

}