package com.InterceptorPractice.controller;

import com.InterceptorPractice.model.User;
import com.InterceptorPractice.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @Autowired
    LoginService loginService;
    @PostMapping("/users/login")
    public ResponseEntity<Object> login(@RequestBody User user) {

        return loginService.login(user);
    }
}
