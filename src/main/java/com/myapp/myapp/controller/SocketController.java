package com.myapp.myapp.controller;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.myapp.myapp.model.LoginRequest;

@Controller
public class SocketController {

    private final SimpMessagingTemplate messagingTemplate;

    public SocketController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    // User ko real-time approval signal bhejna
    public void notifyApproved(Long requestId) {
        messagingTemplate.convertAndSend("/topic/approval/" + requestId, "APPROVED");
    }

    public void notifyRejected(Long requestId) {
        messagingTemplate.convertAndSend("/topic/approval/" + requestId, "REJECTED");
    }

    public void notifyNewRequest(LoginRequest request) {
        messagingTemplate.convertAndSend("/topic/admin/new", request);
    }
}
