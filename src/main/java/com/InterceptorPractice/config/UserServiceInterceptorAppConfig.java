package com.InterceptorPractice.config;

import com.InterceptorPractice.interceptor.LoginServiceInterceptor;
import com.InterceptorPractice.interceptor.RegistrationServiceInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
@Component
public class UserServiceInterceptorAppConfig extends WebMvcConfigurerAdapter {
    @Autowired
    RegistrationServiceInterceptor registrationServiceInterceptor;
    @Autowired
    LoginServiceInterceptor loginServiceInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(registrationServiceInterceptor);
        registry.addInterceptor(loginServiceInterceptor);
    }
}