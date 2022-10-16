package com.InterceptorPractice.controller;

import com.InterceptorPractice.model.User;
import com.InterceptorPractice.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController {
    @Autowired
    RegistrationService registrationService;

    @PostMapping("/users/register")
    public ResponseEntity<Object> register(@RequestBody User user) {
        return registrationService.register(user);
    }
}
