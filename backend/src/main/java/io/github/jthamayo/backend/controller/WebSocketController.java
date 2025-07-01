package io.github.jthamayo.backend.controller;

import java.security.Principal;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import io.github.jthamayo.backend.dto.MessageDto;

@Controller
public class WebSocketController {
    
    private SimpMessagingTemplate messagingTemplate;
    
    public WebSocketController(SimpMessagingTemplate messagingTemplate) {
	this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/send-message")
    public void sendPrivateMessage(MessageDto message, Principal currentUser) {
	messagingTemplate.convertAndSend("/queue/chat/" + message.getChatId(), message.getContent());
    }
}

