package com.InterceptorPractice.controller;

import com.InterceptorPractice.dto.FailureResponseDto;
import com.InterceptorPractice.dto.LoginResponseDto;
import com.InterceptorPractice.model.User;
import com.InterceptorPractice.service.LoginService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = LoginController.class)
public class LoginControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private LoginService loginService;
    String userJson = "{\"id\":\"1\",\"email\":\"eve.holt@reqres.in\",\"password\":\"pistol\"}";
    String userJsonWithoutPassword ="{\"id\":\"1\",\"email\":\"eve.holt@reqres.in\"}";
    @Test
    public void testLogin_Success() throws Exception {
        LoginResponseDto loginResponseDto = new LoginResponseDto();
        loginResponseDto.setToken("abcd@#xyz123");
        when(loginService.login(Mockito.any(User.class))).thenReturn(new ResponseEntity(loginResponseDto,HttpStatus.OK));
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/users/login")
                .accept(MediaType.APPLICATION_JSON).content(userJson)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        System.out.println(response);
    }
    @Test
    public void testLogin_Failure() throws Exception{
        FailureResponseDto failureResponse = new FailureResponseDto();
        failureResponse.setError("Missing Password");
        when(loginService.login(Mockito.any(User.class))).thenReturn(new ResponseEntity<>(failureResponse, HttpStatus.BAD_REQUEST));
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/users/login")
                .accept(MediaType.APPLICATION_JSON).content(userJsonWithoutPassword)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
        System.out.println(response.getStatus());
    }

}
