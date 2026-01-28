package com.myapp.myapp.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myapp.myapp.model.LoginRequest;
import com.myapp.myapp.service.LoginService;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin
public class AdminController {

    private final LoginService loginService;
    private final SocketController socketController;

    public AdminController(LoginService loginService, SocketController socketController) {
        this.loginService = loginService;
        this.socketController = socketController;
    }

    // Sab pending requests dekhne ke liye
    @GetMapping("/pending")
    public List<LoginRequest> getPending() {
        return loginService.getPendingRequests();
    }

    // Approve
    @PostMapping("/approve/{id}")
    public LoginRequest approve(@PathVariable Long id) {
            LoginRequest req = loginService.approve(id);
            socketController.notifyApproved(id);
            return req;
    }   

    // Reject
    @PostMapping("/reject/{id}")
    public LoginRequest reject(@PathVariable Long id) {
        LoginRequest req = loginService.reject(id);
        socketController.notifyRejected(id);
        return req;
    }

}
