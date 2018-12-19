package com.example.demo.services.serviceImpl;


import com.example.demo.dao.UserRepository;
import com.example.demo.entity.organisation.EnumColumnNameForOrg;
import com.example.demo.entity.organisation.Organisation;
import com.example.demo.entity.roles.EnumRole;
import com.example.demo.entity.users.EnumUserColumnNameForUser;
import com.example.demo.entity.users.User;
import com.example.demo.services.LoginService;
import com.example.demo.services.UserService;
import com.example.demo.services.search.MyFilterItem;
import com.example.demo.services.search.OrganisationSpecification;
import com.example.demo.services.search.UserSpecification;
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
        return null;
    }

    @Override
    public User getById(long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()){
            return user.get();
        }else return null;
    }

    @Override
    public User create(User user) {
        user.setChanged(whoCnanged());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User update(User user) {
        user.setChanged(whoCnanged());
        userRepository.save(user);
      return user;
    }

    @Override
    public boolean delete(User user) {
        userRepository.delete(user);
        return true;
    }

    @Override
    public List<User> findByExample(Optional<MyFilterItem> myFilterItem, int offset, int limit)
    {
        List<User> resulList;
        Pageable pageable = PageRequest.of(offset, limit, Sort.by(Sort.Direction.ASC, "id"));
        if (myFilterItem.isPresent()) {
            Specification<User> specification = createSpecification(myFilterItem.get());
            resulList = userRepository.findAll(specification, pageable).getContent();
            return resulList;
        } else {
            resulList = userRepository.findAll();
        }
        System.out.println(resulList.size()+"razmer");
        return resulList;

    }

    @Override
    public int getCount(Optional<MyFilterItem> myFilterItem) {
        int count = 0;
        if (myFilterItem.isPresent()) {
            Specification<User> specification = createSpecification(myFilterItem.get());
            count = Math.toIntExact(userRepository.count(specification));
        }else {
            count = Math.toIntExact(userRepository.count());
        }
        return count;

    }

    private String whoCnanged(){
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
