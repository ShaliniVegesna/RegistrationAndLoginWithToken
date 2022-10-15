package com.InterceptorPractice.service;

import com.InterceptorPractice.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
@Service
public class LoginServiceImpl implements LoginService{
    public ResponseEntity<Object> login(User user) {
        Map<String, String> responseMap = new HashMap<>();
        if(user.getPassword()==null){
            responseMap.put("error","missing password");
            return new ResponseEntity<>(responseMap, HttpStatus.BAD_REQUEST);
        }
        else if(user.getEmail()==null){
            responseMap.put("error","missing email");
            return new ResponseEntity<>(responseMap, HttpStatus.BAD_REQUEST);
        }
        else {
            addToken(user);
            if (user.getToken().equals("abcd@#xyz123")) {

                responseMap.put("token", "abcd@#xyz123");
                return new ResponseEntity<>(responseMap, HttpStatus.OK);
            } else
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
    public void addToken(User user){

        user.setToken("abcd@#xyz123");
    }
}
