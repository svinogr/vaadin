package com.example.demo.config.beans;

import com.example.demo.dao.CarDao;
import com.example.demo.dao.UserDao;
import com.example.demo.dao.UserRepository;
import com.example.demo.dao.daoimpl.CarDaoImpl;
import com.example.demo.dao.daoimpl.UserDaoImpl;
import com.example.demo.entity.users.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

@Configuration
public class DaoBeans {

    @Bean
    UserDao userDao() {
        return new UserDaoImpl();
    }


    @Bean
    CarDao carDao(){
        return new CarDaoImpl();
    }

//    @Bean
//    UserRepository userRepository(){
//        return new UserRepository() {
//            @Override
//            public List<User> findAll() {
//                return userDao().getAll();
//            }
//
//            @Override
//            public List<User> findAll(Sort sort) {
//                return null;
//            }
//
//            @Override
//            public List<User> findAllById(Iterable<Long> longs) {
//                return null;
//            }
//
//            @Override
//            public <S extends User> List<S> saveAll(Iterable<S> entities) {
//                return null;
//            }
//
//            @Override
//            public void flush() {
//
//            }
//
//            @Override
//            public <S extends User> S saveAndFlush(S entity) {
//                return null;
//            }
//
//            @Override
//            public void deleteInBatch(Iterable<User> entities) {
//
//            }
//
//            @Override
//            public void deleteAllInBatch() {
//
//            }
//
//            @Override
//            public User getOne(Long aLong) {
//                return null;
//            }
//
//            @Override
//            public <S extends User> List<S> findAll(Example<S> example) {
//                return null;
//            }
//
//            @Override
//            public <S extends User> List<S> findAll(Example<S> example, Sort sort) {
//                return null;
//            }
//
//            @Override
//            public Page<User> findAll(Pageable pageable) {
//                return null;
//            }
//
//            @Override
//            public <S extends User> S save(S s) {
//                return null;
//            }
//
//            @Override
//            public Optional<User> findById(Long aLong) {
//                return Optional.empty();
//            }
//
//            @Override
//            public boolean existsById(Long aLong) {
//                return false;
//            }
//
//            @Override
//            public long count() {
//                return 0;
//            }
//
//            @Override
//            public void deleteById(Long aLong) {
//
//            }
//
//            @Override
//            public void delete(User user) {
//
//            }
//
//            @Override
//            public void deleteAll(Iterable<? extends User> iterable) {
//
//            }
//
//            @Override
//            public void deleteAll() {
//
//            }
//
//            @Override
//            public <S extends User> Optional<S> findOne(Example<S> example) {
//                return Optional.empty();
//            }
//
//            @Override
//            public <S extends User> Page<S> findAll(Example<S> example, Pageable pageable) {
//                return null;
//            }
//
//            @Override
//            public <S extends User> long count(Example<S> example) {
//                return 0;
//            }
//
//            @Override
//            public <S extends User> boolean exists(Example<S> example) {
//                return false;
//            }
//        };
//    }
//

}


