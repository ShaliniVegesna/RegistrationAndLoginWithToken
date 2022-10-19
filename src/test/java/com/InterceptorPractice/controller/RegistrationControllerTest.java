package com.InterceptorPractice.controller;

import com.InterceptorPractice.dto.FailureResponseDto;
import com.InterceptorPractice.dto.RegisterResponseDto;
import com.InterceptorPractice.model.User;
import com.InterceptorPractice.service.RegistrationService;
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
@WebMvcTest(value = RegistrationController.class)
public class RegistrationControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private RegistrationService registrationService;
    String userJson = "{\"id\":\"1\",\"email\":\"eve.holt@reqres.in\",\"password\":\"pistol\"}";
    String userJsonWithoutPassword ="{\"id\":\"1\",\"email\":\"eve.holt@reqres.in\"}";
    @Test
    public void testRegistration_Success() throws Exception {
        RegisterResponseDto registerResponseDto = new RegisterResponseDto();
        registerResponseDto.setId(1L);
        registerResponseDto.setToken("abcd@#xyz123");
        when(registrationService.register(Mockito.any(User.class))).thenReturn(new ResponseEntity(registerResponseDto, HttpStatus.OK));
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/users/register")
                .accept(MediaType.APPLICATION_JSON).content(userJson)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        System.out.println(response);
    }
    @Test
    public void testRegister_Failure() throws Exception{
        FailureResponseDto failureResponse = new FailureResponseDto();
        failureResponse.setError("Missing Password");
        when(registrationService.register(Mockito.any(User.class))).thenReturn(new ResponseEntity<>(failureResponse, HttpStatus.BAD_REQUEST));
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/users/register")
                .accept(MediaType.APPLICATION_JSON).content(userJsonWithoutPassword)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
        System.out.println(response.getStatus());
    }

}

