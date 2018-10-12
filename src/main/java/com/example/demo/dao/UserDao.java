package com.example.demo.dao;


import com.example.demo.entity.users.User;

public interface UserDao extends BasicDao<User> {
    User getByLogin(String login);
}
