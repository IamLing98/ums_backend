package com.linkdoan.backend.model;

import com.linkdoan.backend.dto.UserRoleDTO;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "user_role")
@Data
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)

    @Column(name = "id")
    private Long id;

    @Column(name = "role_id")
    private Long roleId;


    @Column(name = "user_id")
    private Long userId;

    public UserRoleDTO toDto() {
        UserRoleDTO userRoleDTO = new UserRoleDTO();
        userRoleDTO.setRoleId(roleId);
        userRoleDTO.setUserId(userId);
        return userRoleDTO;
    }
}