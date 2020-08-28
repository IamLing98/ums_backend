package com.linkdoan.backend.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.linkdoan.backend.base.anotation.AdjHistory;
import com.linkdoan.backend.model.User;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDTO  {
    @NotNull
    @AdjHistory(field =  "userId")
    private Long userId;

    @NotNull
    @AdjHistory(field = "username")
    private String username;

    @AdjHistory(field = "password")
    private String password;

    @AdjHistory(field = "firstName")
    private String firstName;

    @AdjHistory(field = "lastName")
    private String lastName;

    @AdjHistory(field = "createdAt")
    private Date createdAt;

    @AdjHistory(field = "updatedAt")
    private Date updatedAt;

    @AdjHistory(field = "email")
    private String email;

    @AdjHistory(field = "isActive")
    private int isActive;

    @AdjHistory(field = "isEmailVerified")
    private int isEmailVerified;

    @AdjHistory(field = "ownerId")
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
