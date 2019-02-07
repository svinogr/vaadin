package com.example.demo.services.serviceImpl;


import com.example.demo.dao.UserRepository;
import com.example.demo.entity.roles.EnumRole;
import com.example.demo.entity.users.EnumUserColumnNameForUser;
import com.example.demo.entity.users.User;
import com.example.demo.entity.users.UserInfo;
import com.example.demo.services.LoginService;
import com.example.demo.services.UniqTestInterface;
import com.example.demo.services.UserService;
import com.example.demo.services.search.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
public class UserServiceImpl implements UserService, UniqTestInterface {

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
        User saveUser = null;
        User userByLogin = getUserByLogin(user.getLogin());

        if (userByLogin == null) {

            user.setChanged(whoCnanged());
            user.setPassword(passwordEncoder.encode(user.getTempField()));
            saveUser = userRepository.save(user);

        }

        return saveUser;
    }

    @Override
    public User update(User user) {
        User savedUser = null;

        if (user.getTempField() != null) {
            if (!passwordEncoder.matches(user.getTempField(), user.getPassword())) {
                user.setPassword(passwordEncoder.encode(user.getTempField()));
            }
        }
        try {

            savedUser = userRepository.save(user);


        } catch (DataIntegrityViolationException e) {
            System.out.println("такой логин уже есть с логином, но это не точно");
        }

        return savedUser;
    }

    @Override
    public boolean delete(User user) {
        userRepository.delete(user);
        return true;
    }

    @Override
    public List<User> saveList(List<User> list) {
        return list;
    }

    @Override
    public User createDefaultUserAdmin() {
        User admin = getUserByLogin("a");
        if (admin == null) {
            admin = new User();
            admin.setRole(EnumRole.ROLE_ADMIN);
            admin.setLogin("a");
            admin.setPassword(passwordEncoder.encode("a"));
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
        Pageable pageable = PageRequest.of(offset / limit, limit, Sort.by(Sort.Direction.ASC, "id"));
        if (myFilterItem.isPresent()) {
            Specification<User> specification = createSpecification(myFilterItem.get());
            resulList = userRepository.findAll(specification, pageable).getContent();
            return resulList;
        } else {
            resulList = userRepository.findAll(pageable).getContent();
        }
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
        List<User> resulList;
        if (myFilterItem.isPresent()) {
            Specification<User> carSpecification = createSpecification(myFilterItem.get());
            resulList = userRepository.findAll(carSpecification);
            return resulList;
        } else {
            resulList = userRepository.findAll();
        }
        return resulList;
    }

    @Override
    public boolean isUniq(MyFilterItem myFilter, long id) {
        Optional<MyFilterItem> optionalMyFilterItem = Optional.of(myFilter);
        List<User> list = findByExampleWithoutPagable(optionalMyFilterItem);

        if (list.size() > 0) {
            if (list.get(0).getId() == id) {
                return true;
            } else return false;

        }
        return true;

//        if (userByLogin == null) {
//            return true;
//        } else if (userByLogin.getId() == id) {
//            return true;
//        } else return false;
    }
}
