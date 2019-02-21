package com.example.demo.config.beans;

import com.example.demo.services.LoginService;
import com.example.demo.services.serviceImpl.LoginServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceBeans {

    @Bean
    LoginService loginService(){
        return new LoginServiceImpl();
    }
}
