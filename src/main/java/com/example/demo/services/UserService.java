package com.example.demo.services;


import com.example.demo.entity.users.User;

public interface UserService extends ItemService<User> {
    User getUserByLogin(String login);

    User update(User user);

    boolean delete(User user);

    User createDefaultUserAdmin();

}
