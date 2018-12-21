package com.example.demo.services.serviceImpl;


import com.example.demo.dao.UserRepository;
import com.example.demo.entity.roles.EnumRole;
import com.example.demo.entity.users.EnumUserColumnNameForUser;
import com.example.demo.entity.users.User;
import com.example.demo.entity.users.UserInfo;
import com.example.demo.services.LoginService;
import com.example.demo.services.UserService;
import com.example.demo.services.search.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    LoginService loginService;

    @Override
    public User getUserByLogin(String login) {
        MyFilterItem myFilterItem = new OneTextValue(EnumUserColumnNameForUser.LOGIN);
        Searchable oneTextSearch = new OneTextSearch(login);
        myFilterItem.setSearchable(oneTextSearch);
        Specification<User> specification = createSpecification(myFilterItem);
        User user = null;
        List<User> userList = userRepository.findAll(specification);
        if (userList.size() > 0) {
            user = userList.get(0);
        }
        return user;
    }

    @Override
    public User getById(long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return user.get();
        } else return null;
    }


    @Override
    public User create(User user) {
        User saveUser = new User();
        User userByLogin = getUserByLogin(user.getLogin());
        if (userByLogin == null) {
            //тоесть новый
            if (user.getPassword() != null && user.getLogin() != null) {
                if (!user.getPassword().isEmpty() && !user.getLogin().isEmpty()) {
                    System.out.println(1);
                    user.setChanged(whoCnanged());
                    user.setPassword(passwordEncoder.encode(user.getPassword()));
                    saveUser = userRepository.save(user);
                }
            }
        }

        return saveUser;
    }

    @Override
    public User update(User user) {
        System.out.println(user.getLogin() + "-eeeeeeee" + user.getPassword());
        User userUpdate = null;
        User userByLogin = getUserByLogin(user.getLogin());

        if (userByLogin == null) {
            if (!user.getLogin().isEmpty()) {
                user.setChanged(whoCnanged());

                if (!user.getPassword().isEmpty()) {
                    user.setPassword(passwordEncoder.encode(user.getPassword()));
                }
                userUpdate = userRepository.save(user);
            }
        }
        return userUpdate;
    }

    @Override
    public boolean delete(User user) {
        userRepository.delete(user);
        return true;
    }

    @Override
    public User createDefaultUserAdmin() {
        User admin = getUserByLogin("admin");
        if (admin == null) {
            admin = new User();
            admin.setRole(EnumRole.ROLE_ADMIN);
            admin.setLogin("admin");
            admin.setPassword(passwordEncoder.encode("123"));
            admin.setChanged(new Date().toString());
            UserInfo userInfo = new UserInfo();
            userInfo.setName("Билл");
            userInfo.setSurname("Гейтц");
            userInfo.setPatronymic("Досович");

            admin.setUserInfo(userInfo);
            userRepository.save(admin);
        }
        return admin;
    }

    @Override
    public List<User> findByExample(Optional<MyFilterItem> myFilterItem, int offset, int limit) {
        List<User> resulList;
        Pageable pageable = PageRequest.of(offset, limit, Sort.by(Sort.Direction.ASC, "id"));
        if (myFilterItem.isPresent()) {
            Specification<User> specification = createSpecification(myFilterItem.get());
            resulList = userRepository.findAll(specification, pageable).getContent();
            return resulList;
        } else {
            resulList = userRepository.findAll();
        }
        System.out.println(resulList.size() + "razmer");
        return resulList;

    }

    @Override
    public int getCount(Optional<MyFilterItem> myFilterItem) {
        int count = 0;
        if (myFilterItem.isPresent()) {
            Specification<User> specification = createSpecification(myFilterItem.get());
            count = Math.toIntExact(userRepository.count(specification));
        } else {
            count = Math.toIntExact(userRepository.count());
        }
        return count;

    }

    private String whoCnanged() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(loginService.getAuth().getUsername());
        stringBuilder.append(" ");
        stringBuilder.append(new Date());
        return stringBuilder.toString();
    }

    private Specification<User> createSpecification(MyFilterItem myFilterItem) {
        Specification<User> specification = null;
        EnumUserColumnNameForUser enumUserColumnNameForUser = (EnumUserColumnNameForUser) myFilterItem.getEnumColumnNamesFor();
        switch (enumUserColumnNameForUser) {
            case SURNAME:
                specification = UserSpecification.getBySURNAME(myFilterItem);
                break;
            case LOGIN:
                specification = UserSpecification.getByLogin(myFilterItem);
                break;
            default:
                System.out.println("не удалсоь найти спецификацию");
        }
        return specification;
    }

    @Override
    public List<User> findByExampleWithoutPagable(Optional<MyFilterItem> myFilterItem) {
        return null;
    }

}
