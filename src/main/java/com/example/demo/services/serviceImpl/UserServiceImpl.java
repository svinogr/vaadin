package com.example.demo.services.serviceImpl;


import com.example.demo.dao.UserRepository;
import com.example.demo.entity.roles.EnumRole;
import com.example.demo.entity.users.User;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

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
        User createUser = userRepository.save(user);
        securityFields(user);
        return createUser;
    }

    @Override
    public User createAdmin(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(EnumRole.ROLE_ADMIN.name());
        User createUser = userRepository.save(user);
        securityFields(user);
        return createUser;
    }

    @Override
    public boolean update(User user) {
       userRepository.save(user);
      securityFields(user);
      return true;
    }

    @Override
    public boolean delete(User user) {
        userRepository.delete(user);
        return true;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();
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
