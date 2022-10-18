package com.InterceptorPractice.service;

import com.InterceptorPractice.dto.FailureResponseDto;
import com.InterceptorPractice.dto.LoginResponseDto;
import com.InterceptorPractice.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
@Service
public class LoginServiceImpl implements LoginService{
    public ResponseEntity<?> login(User user) {
        if(user.getPassword()==null){
            FailureResponseDto failureResponse = new FailureResponseDto();
            failureResponse.setError("Missing Password");
            return new ResponseEntity<>(failureResponse, HttpStatus.BAD_REQUEST);
        }
        else {
            addToken(user);
            if (user.getToken().equals("abcd@#xyz123")) {
                LoginResponseDto loginResponseDto = new LoginResponseDto();
                loginResponseDto.setToken("abcd@#xyz123");
                return new ResponseEntity<>(loginResponseDto, HttpStatus.OK);
            } else
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
    public void addToken(User user){
        user.setToken("abcd@#xyz123");
    }
}
