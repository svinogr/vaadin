package com.example.demo.services;

import com.example.demo.entity.roles.EnumRole;

public interface LoginService {
    EnumRole login(String name, String password, boolean isAdmin);

    boolean logout();
}
