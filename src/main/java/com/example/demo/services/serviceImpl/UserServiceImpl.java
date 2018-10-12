package com.example.demo.services.serviceImpl;


import com.example.demo.dao.UserDao;
import com.example.demo.entity.roles.EnumRole;
import com.example.demo.entity.users.User;
import com.example.demo.services.UserService;
import com.vaadin.flow.spring.annotation.SpringComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public User getUserByLogin() {
        return null;
    }

    @Override
    public User createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(EnumRole.ROLE_USER.name());
        User createUser = userDao.create(user);
        securityFields(user);
        return createUser;
    }

    @Override
    public User createAdmin(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(EnumRole.ROLE_ADMIN.name());
        User createUser = userDao.create(user);
        securityFields(user);
        return createUser;
    }

    @Override
    public boolean update(User user) {
      boolean update =  userDao.update(user);
     securityFields(user);
      return update;
    }

    @Override
    public boolean delete(User user) {
        boolean delete = userDao.delete(user);
        return delete;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = userDao.getAll();
        for (User user : users) {
            securityFields(user);
        }
        return users;
    }

    private void securityFields(User user){
        user.setPassword("");
        user.setRole("");
        user.setRole("");
    }
}
