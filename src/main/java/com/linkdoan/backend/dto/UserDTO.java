package com.linkdoan.backend.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.linkdoan.backend.base.anotation.AdjHistory;
import com.linkdoan.backend.model.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDTO  {
    @NotNull
    @AdjHistory(field =  "user_id")
    private Long userId;
    @NotNull
    @AdjHistory(field = "username")
    private String username;

    @AdjHistory(field = "password")
    private String password;

    @AdjHistory(field = "first_name")
    private String firstName;

    @AdjHistory(field = "last_name")
    private String lastName;

    @AdjHistory(field = "created_at")
    private Date createdAt;

    @AdjHistory(field = "updated_at")
    private Date updatedAt;

    @AdjHistory(field = "email")
    private String email;

    @AdjHistory(field = "is_active")
    private int isActive;

    @AdjHistory(field = "is_email_verified")
    private int isEmailVerified;

    @AdjHistory(field = "owner_id")
    private String ownerId;



    public User toModel(){
        User user= new User();
        user.setUserId(userId);
        user.setCreatedAt(createdAt);
        user.setEmail(email);
        user.setFirstName(firstName);
        user.setIsActive(isActive);
        user.setIsEmailVerified(isEmailVerified);
        user.setLastName(lastName);
        user.setOwnerId(ownerId);
        user.setUpdatedAt(updatedAt);
        user.setPassword(password);
        user.setUsername(username);
        return user;
    }
}
