package com.example.demo.services;


import com.example.demo.entity.users.User;

import java.util.List;

public interface UserService extends ItemService<User> {
    User getUserByLogin();
    User createUser(User user);
    User createAdmin(User user);
    User update(User user);
    boolean delete(User user);

}
