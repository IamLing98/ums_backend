package com.linkdoan.backend.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NotificationDTO {

    String senderUsername;

    String receiverUsername;

    String content;
}
