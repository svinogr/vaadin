//package com.example.demo.services.serviceImpl;
//
//import app.entity.cars.car.Car;
//import app.entity.users.User;
//import app.services.ValidService;
//import org.springframework.stereotype.Component;
//import org.springframework.validation.BindingResult;
//import org.springframework.validation.FieldError;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@Component
//public class ValidUserImpl implements ValidService {
//    @Override
//    public User validUser(User user, BindingResult result) {
//        Map<String, String> map = new HashMap<>();
//
//        for(FieldError fieldError: result.getFieldErrors()){
//            map.put(fieldError.getField(), fieldError.getDefaultMessage());
//        }
//
//        if(map.containsKey("login")){
//            user.setLogin(map.get("login"));
//        }
//        if(map.containsKey("password")){
//            user.setPassword(map.get("password"));
//        }
//
//        return user;
//
//    }
//
//    @Override
//    public Car validCar(Car car, BindingResult bindingResult) {
//        return null;
//    }
//}
