package com.myapp.myapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myapp.myapp.model.ApprovalStatus;
import com.myapp.myapp.model.LoginRequest;

public interface LoginRequestRepository extends JpaRepository<LoginRequest, Long> {

    // saare pending requests laane ke liye (admin panel ke liye)
    List<LoginRequest> findByStatus(ApprovalStatus status);
}
