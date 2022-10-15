package com.InterceptorPractice.service;

import com.InterceptorPractice.model.User;
import org.springframework.http.ResponseEntity;

public interface LoginService {

    ResponseEntity<Object> login(User user);
}
