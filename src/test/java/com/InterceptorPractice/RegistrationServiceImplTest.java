package com.InterceptorPractice;

import com.InterceptorPractice.model.User;
import com.InterceptorPractice.repository.RegistrationRepository;
import com.InterceptorPractice.service.RegistrationServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RegistrationServiceImplTest {
    @Mock
    private RegistrationRepository registrationRepository;
    @InjectMocks
    private RegistrationServiceImpl registrationService;
    @Test
    public void testRegister_Success(){
      User user = new User();
      user.setId(1L);
      user.setEmail("eve.holt@reqres.in");
      user.setPassword("pistol");
      User savedUser = new User();
      savedUser.setId(1L);
      savedUser.setEmail("eve.holt@reqres.in");
      savedUser.setPassword("eve.holt@reqres.in");
      savedUser.setToken("abcd@#xyz123");
      when(registrationRepository.save(user)).thenReturn(savedUser);
      Assertions.assertEquals(registrationService.register(user).getStatusCode(),HttpStatus.OK);
    }
    @Test
    public void testRegister_Unsuccessful(){
        User user = new User();
        user.setId(1L);
        user.setEmail("eve.holt@reqres.in");
        Assertions.assertEquals(registrationService.register(user).getStatusCode(),HttpStatus.BAD_REQUEST);
    }
}
