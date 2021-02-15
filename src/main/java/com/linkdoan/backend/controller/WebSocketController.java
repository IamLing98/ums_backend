package com.linkdoan.backend.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import java.text.DateFormat;
import java.util.Date;

@Controller
@Slf4j
public class WebSocketController {

    private final SimpMessagingTemplate template;

    @Autowired
    WebSocketController(SimpMessagingTemplate template) {
        this.template = template;
    }

    @Scheduled(fixedRate = 5000)
    public void sendUpdate() {
        String formattedDate = DateFormat.getTimeInstance().format(new Date());
        log.debug("Sending Update {}", formattedDate);
        this.template.convertAndSend("/topic/update", formattedDate);
    }

    @MessageMapping("/notifications")
    @SendToUser("/queue/notifications")
    public String reply(@Payload String message ) {
        this.template.convertAndSendToUser("517100032", "/queue/notifications", "Hello " + "send from vandoan547");
        return "Hello " + message;
    }
}
