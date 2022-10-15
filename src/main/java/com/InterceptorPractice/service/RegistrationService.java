package com.InterceptorPractice.service;

import com.InterceptorPractice.model.User;
import org.springframework.http.ResponseEntity;

public interface RegistrationService {
    ResponseEntity<Object> register(User user);
}
