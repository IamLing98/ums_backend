package com.linkdoan.backend.model;

import com.linkdoan.backend.dto.UserDTO;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "user")
@Data
public class User {
    @Id
    @SequenceGenerator(name = "pk_sequence", sequenceName = "user_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

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
        userDTO.setFirstName(firstName);
        userDTO.setIsActive(isActive);
        userDTO.setIsEmailVerified(isEmailVerified);
        userDTO.setLastName(lastName);
        userDTO.setOwnerId(ownerId);
        userDTO.setUpdatedAt(updatedAt);
        userDTO.setPassword(password);
        userDTO.setUsername(username);
        return userDTO;
    }
}
