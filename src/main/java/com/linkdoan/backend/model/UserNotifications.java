package com.linkdoan.backend.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users_notification")
@Data
public class UserNotifications {
    @Id
    @Column(name = "id", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "sender_id")
    private String senderId;

    @Column(name = "receiver_id")
    private String receiverId;

    @Column(name = "status", columnDefinition = "INT")
    private Long status;

    @Column(name = "notification_id")
    Long notificationId;

    @Column(name = "created_date", columnDefinition = "DATETIME")
    LocalDateTime createdDate;
}
