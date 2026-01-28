package com.myapp.myapp.controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myapp.myapp.dto.LoginDto;
import com.myapp.myapp.model.LoginRequest;
import com.myapp.myapp.service.LoginService;

@RestController
@RequestMapping("/api/login")
@CrossOrigin
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    // Fake login submit
    @PostMapping
    public LoginRequest login(@RequestBody LoginDto dto) {
        return loginService.createRequest(dto.getUsername(), dto.getPassword());
    }
}
