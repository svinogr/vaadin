package com.example.demo.entity.users;

import com.example.demo.entity.Selectable;
import com.example.demo.entity.roles.EnumRole;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "user_entity")
public class User implements Selectable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_info_id")
    private UserInfo userInfo;

    @NotNull
    @Length(min = 1, max = 20, message = "длинна должна быть от 1 до 20")
    @Column(name = "login", unique = true)
    private String login;

    @NotNull
    @Length(min = 1, max = 200, message = "длинна должна быть от 1 до 200")
    @Column(name = "password")
    private String password;

    @Enumerated(value = EnumType.STRING)
    private EnumRole role;

    public User() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public EnumRole getRole() {
        return role;
    }

    public void setRole(EnumRole role) {
        this.role = role;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
