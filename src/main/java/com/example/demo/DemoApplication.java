package com.example.demo;

import com.example.demo.entity.roles.EnumRole;
import com.example.demo.entity.users.User;
import com.example.demo.entity.users.UserInfo;
import com.example.demo.services.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication(exclude = {
        DataSourceAutoConfiguration.class,
        DataSourceTransactionManagerAutoConfiguration.class,
})

public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
        ApplicationContext context = new AnnotationConfigApplicationContext();
        UserService userService = (UserService) context.getBean("userService");

        User admin = new User();
        admin.setRole(EnumRole.ROLE_ADMIN);
        admin.setLogin("admin");
        admin.setPassword("123");

        UserInfo userInfo = new UserInfo();
        userInfo.setName("Билл");
        userInfo.setSurname("Гейтц");
        userInfo.setPatronymic("Досович");

        admin.setUserInfo(userInfo);
        userService.create(admin);


    }


}
