package com.InterceptorPractice.service;

import com.InterceptorPractice.dto.FailureResponseDto;
import com.InterceptorPractice.dto.RegisterResponseDto;
import com.InterceptorPractice.model.User;
import com.InterceptorPractice.repository.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class RegistrationServiceImpl implements RegistrationService {
    @Autowired
    private RegistrationRepository registrationRepository;
    @Override
    public ResponseEntity<Object> register(User user) {
        RegisterResponseDto registerResponse = new RegisterResponseDto();
        FailureResponseDto failureResponse = new FailureResponseDto();
        if(user.getPassword()==null){
            failureResponse.setError("missing password");
            return new ResponseEntity<>(failureResponse, HttpStatus.BAD_REQUEST);
        }
        else{
            user.setToken("abcd@#xyz123");
            registrationRepository.save(user);
            registerResponse.setId(user.getId());
            registerResponse.setToken(user.getToken());
            return new ResponseEntity<>(registerResponse, HttpStatus.OK);
        }
    }
}
