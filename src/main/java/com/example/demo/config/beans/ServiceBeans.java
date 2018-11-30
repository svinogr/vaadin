package com.example.demo.config.beans;

import com.example.demo.services.LoginService;
import com.example.demo.services.UserService;
import com.example.demo.services.serviceImpl.LoginServiceImpl;
import com.example.demo.services.serviceImpl.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceBeans {

    @Bean
    UserService userService(){
        return new UserServiceImpl();
    }
//
//    @Bean
//    CarService carService(){
//       return new CarServiceImpl();
//    }

    @Bean
    LoginService loginService(){
        return new LoginServiceImpl();
    }
}
