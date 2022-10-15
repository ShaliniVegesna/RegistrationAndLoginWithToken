package com.InterceptorPractice.service;

import com.InterceptorPractice.model.User;
import com.InterceptorPractice.repository.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class RegistrationServiceImpl implements RegistrationService {
    @Autowired
    private RegistrationRepository registrationRepository;
    @Override
    public ResponseEntity<Object> register(User user) {
        Map<String, Object> userMap = new HashMap<>();
        if(user.getPassword()==null){
            userMap.put("error","missing password");
            return new ResponseEntity<>(userMap, HttpStatus.BAD_REQUEST);
        }
        else if(user.getEmail()==null){
            userMap.put("error","missing email");
            return new ResponseEntity<>(userMap, HttpStatus.BAD_REQUEST);
        }
        else{
            registrationRepository.save(user);
            userMap.put("id", user.getId());
            userMap.put("token", "abcd@#xyz123");
            return new ResponseEntity<>(userMap, HttpStatus.OK);
        }
    }
}
