package com.linkdoan.backend.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.linkdoan.backend.model.User;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDTO {
    @NotNull
    private Long userId;

    @NotNull
    private String username;

    private String password;

    private Date createdAt;

    private Date updatedAt;

    private String email;

    private int isActive;

    private int isEmailVerified;

    private String ownerId;

    private List<RoleDTO> roles;


    public User toModel() {
        User user = new User();
        user.setUserId(userId);
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
