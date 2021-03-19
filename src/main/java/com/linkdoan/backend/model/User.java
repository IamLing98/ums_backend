package com.linkdoan.backend.model;

import com.linkdoan.backend.dto.UserDTO;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;


@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "email")
    private String email;

    @Column(name = "is_active")
    private Integer isActive;

    @Column(name = "is_email_verified")
    private Integer isEmailVerified;

    @Column(name = "owner_id")
    private String ownerId;

    @Column(name = "role_id")
    private Long roleId;

    public UserDTO toDto() {
        UserDTO userDTO = new UserDTO();
        userDTO.setCreatedAt(createdAt);
        userDTO.setEmail(email);
        userDTO.setIsActive(isActive);
        userDTO.setIsEmailVerified(isEmailVerified);
        userDTO.setOwnerId(ownerId);
        userDTO.setUpdatedAt(updatedAt);
        userDTO.setPassword(password);
        userDTO.setUsername(username);
        return userDTO;
    }
}
