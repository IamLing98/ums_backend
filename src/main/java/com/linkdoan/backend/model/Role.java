package com.linkdoan.backend.model;

import com.linkdoan.backend.dto.RoleDTO;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "role")
@Data // lombok
public class Role {

    @Id
    @SequenceGenerator(name="pk_sequence_role",sequenceName="SYS_ROLES_SEQ")
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="pk_sequence_role")
    @Column(name = "role_id")
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