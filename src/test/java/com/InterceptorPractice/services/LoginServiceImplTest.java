package com.InterceptorPractice.services;

import com.InterceptorPractice.model.User;
import com.InterceptorPractice.repository.LoginRepository;
import com.InterceptorPractice.service.LoginServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

@ExtendWith(MockitoExtension.class)
public class LoginServiceImplTest {
@Mock
    private LoginRepository loginRepository;
@InjectMocks
   private LoginServiceImpl loginService;
    @Test
    public void testLogin_Success(){
        User user = new User();
        user.setId(1L);
        user.setEmail("eve.holt@reqres.in");
        user.setPassword("pistol");
        Assertions.assertEquals(loginService.login(user).getStatusCode(), HttpStatus.OK);
    }
    @Test
    public void testLogin_UnSuccessful() {
        User user = new User();
        user.setId(1L);
        user.setEmail("eve.holt@reqres.in");
        Assertions.assertEquals(loginService.login(user).getStatusCode(), HttpStatus.BAD_REQUEST);
    }
}
