package com.linkdoan.backend.model;

import com.linkdoan.backend.dto.UserDTO;
import lombok.Data;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "users")
@Data
@BatchSize(size = 10)
public class User  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    @Column(name = "user_id", unique = true)
    private Long userId;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(name = "email")
    private String email;

    @Column(name = "is_active")
    private int isActive;

    @Column(name = "is_email_verified")
    private int isEmailVerified;

    @Column(name = "owner_id")
    private String ownerId;

    public UserDTO toDto() {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(userId);
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
