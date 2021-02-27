package com.linkdoan.backend.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.linkdoan.backend.model.User;
import lombok.Data;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Map;

@Data
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

    private Integer isActive;

    private Integer isEmailVerified;

    private String ownerId;

    private RoleDTO roleDTO;

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
