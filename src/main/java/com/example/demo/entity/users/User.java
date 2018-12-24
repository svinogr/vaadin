package com.example.demo.entity.users;

import com.example.demo.entity.Selectable;
import com.example.demo.entity.roles.EnumRole;

import javax.persistence.*;

@Entity
@Table(name = "user_entity")
public class User implements Selectable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "changed")
    private String changed;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_info_id")
    private UserInfo userInfo;

    @Column(name = "login", unique = true)
    private String login;

    @Column(name = "password")
    private String password;

    @Enumerated(value = EnumType.STRING)
    private EnumRole role;

    @Transient
    private String tempField;

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

    public String getChanged() {
        return changed;
    }

    public void setChanged(String changed) {
        this.changed = changed;
    }


    public String getTempField() {
        return tempField;
    }

    public void setTempField(String tempField) {
        this.tempField = tempField;
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
