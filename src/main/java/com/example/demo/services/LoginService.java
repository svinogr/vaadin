package com.example.demo.services;

import com.example.demo.entity.roles.EnumRole;
import org.springframework.security.core.userdetails.UserDetails;

public interface LoginService {
    EnumRole login(String name, String password);

    UserDetails getAuth();

    void logout();

    EnumRole getRole();
}
