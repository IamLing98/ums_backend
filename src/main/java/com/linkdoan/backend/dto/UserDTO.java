package com.linkdoan.backend.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.linkdoan.backend.model.Role;
import com.linkdoan.backend.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    @NotNull
    private String username;

    private String password;

    private String newPassword;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private String email;

    private Integer isActive;

    private Integer isEmailVerified;

    private String ownerId;

    private RoleDTO roleDTO;

    private String roleName;

    private Role role;

    public UserDTO(String username, String password, LocalDateTime createdAt, LocalDateTime updatedAt, String email, Integer isActive, Integer isEmailVerified, String ownerId, Role role) {
        this.username = username;
        this.password = password;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.email = email;
        this.isActive = isActive;
        this.isEmailVerified = isEmailVerified;
        this.ownerId = ownerId;
        this.role = role;
    }

    public User toModel() {
        User user = new User();
        user.setCreatedAt(createdAt);
        user.setEmail(email);
        user.setIsActive(isActive);
        user.setIsEmailVerified(isEmailVerified);
        user.setOwnerId(ownerId);
        user.setUpdatedAt(updatedAt);
        user.setPassword(password);
        user.setUsername(username);
        return user;
    }
}
