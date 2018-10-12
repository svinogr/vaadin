package com.example.demo.services;


import com.example.demo.entity.users.User;

import java.util.List;

public interface UserService {
    User getUserByLogin();
    User createUser(User user);
    User createAdmin(User user);
    boolean update(User user);
    boolean delete(User user);

    List<User> getAllUsers();

}
