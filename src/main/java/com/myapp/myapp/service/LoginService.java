package com.myapp.myapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.myapp.myapp.controller.SocketController;
import com.myapp.myapp.model.ApprovalStatus;
import com.myapp.myapp.model.LoginRequest;
import com.myapp.myapp.repository.LoginRequestRepository;

@Service
public class LoginService {

    private final LoginRequestRepository loginRequestRepository;
    private final SocketController socketController;

    public LoginService(LoginRequestRepository loginRequestRepository, SocketController socketController) {
        this.loginRequestRepository = loginRequestRepository;
        this.socketController = socketController;
    }

    // User ne fake login kiya
    public LoginRequest createRequest(String username, String password) {
        LoginRequest request = new LoginRequest(username, password);
        LoginRequest saved = loginRequestRepository.save(request);
        socketController.notifyNewRequest(saved); // real-time admin update
        return saved;
    }

    // Admin ko pending list dikhani
    public List<LoginRequest> getPendingRequests() {
        return loginRequestRepository.findByStatus(ApprovalStatus.PENDING);
    }

    // Admin approve kare
    public LoginRequest approve(Long id) {
        LoginRequest req = loginRequestRepository.findById(id).orElseThrow();
        req.setStatus(ApprovalStatus.APPROVED);
        return loginRequestRepository.save(req);
    }

    // Admin reject kare
    public LoginRequest reject(Long id) {
        LoginRequest req = loginRequestRepository.findById(id).orElseThrow();
        req.setStatus(ApprovalStatus.REJECTED);
        return loginRequestRepository.save(req);
    }
}
