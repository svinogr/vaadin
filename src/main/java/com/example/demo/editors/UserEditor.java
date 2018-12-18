package com.example.demo.editors;

import com.example.demo.entity.roles.EnumRole;
import com.example.demo.entity.users.User;
import com.example.demo.entity.users.UserInfo;
import com.example.demo.services.UserService;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Setter;
import com.vaadin.flow.function.ValueProvider;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;

@SpringComponent
@UIScope
public class UserEditor extends AbstarctEditor<User> {
    public UserEditor(UserService itemService) {
        super(itemService);
    }

    @Override
    void setTitle() {
        title.setText("Карточка пользователя");
    }

    @Override
    void createTabs(Tabs tabs) {
        Tab general = createGeneralTab();
        tabs.add(general);
    }

    private Tab createGeneralTab() {
        Tab general = new Tab("Основное");

        VerticalLayout oneLayout = new VerticalLayout();

        HorizontalLayout subOneLayoutH = new HorizontalLayout();
        subOneLayoutH.setAlignItems(Alignment.BASELINE);

        TextField surname = new TextField("Фамилия");
        binder.forField(surname).bind(new ValueProvider<User, String>() {
            @Override
            public String apply(User user) {
                return user.getUserInfo().getSurname();
            }
        }, new Setter<User, String>() {
            @Override
            public void accept(User user, String s) {
                user.getUserInfo().setSurname(s);
            }
        });

        TextField name = new TextField("Имя");
        binder.forField(name).bind(new ValueProvider<User, String>() {
            @Override
            public String apply(User user) {
                return user.getUserInfo().getName();
            }
        }, new Setter<User, String>() {
            @Override
            public void accept(User user, String s) {
                user.getUserInfo().setName(s);
            }
        });

        TextField patronymic = new TextField("Отчество");
        binder.forField(patronymic).bind(new ValueProvider<User, String>() {
            @Override
            public String apply(User user) {
                return user.getUserInfo().getPatronymic();
            }
        }, new Setter<User, String>() {
            @Override
            public void accept(User user, String s) {
                user.getUserInfo().setPatronymic(s);
            }
        });

        Checkbox admin = new Checkbox("Администратор");
        binder.forField(admin).bind(new ValueProvider<User, Boolean>() {
            @Override
            public Boolean apply(User user) {
                if (user.getRole() == EnumRole.ROLE_ADMIN) {
                    return true;
                } else return false;
            }
        }, new Setter<User, Boolean>() {
            @Override
            public void accept(User user, Boolean aBoolean) {
                if (aBoolean) {
                    user.setRole(EnumRole.ROLE_ADMIN);
                } else user.setRole(EnumRole.ROLE_USER);
            }
        });


        subOneLayoutH.add(surname, name, patronymic, admin);

        HorizontalLayout subTwoLayoutH = new HorizontalLayout();
        subTwoLayoutH.setAlignItems(Alignment.BASELINE);

        TextField login = new TextField("Логин");
        binder.forField(login).bind(new ValueProvider<User, String>() {
            @Override
            public String apply(User user) {
                return user.getLogin();
            }
        }, new Setter<User, String>() {
            @Override
            public void accept(User user, String s) {
                user.setLogin(s);
            }
        });

        Checkbox changePass = new Checkbox("Изменить пароль", false);
        PasswordField password = new PasswordField("Пароль");
        binder.forField(password).bind(new ValueProvider<User, String>() {
            @Override
            public String apply(User user) {
                System.out.println("eddeded");
                if(user.getPassword() == null){
                    password.setEnabled(true);
                    changePass.setVisible(false);
                    changePass.setValue(true);

                }else {
                    password.setEnabled(false);
                    changePass.setVisible(true);
                    changePass.setValue(false);
                }
                //password.setEnabled(true);
                return "";
            }
        }, new Setter<User, String>() {
            @Override
            public void accept(User user, String s) {
                if (changePass.getValue() && !s.isEmpty()) {
                    user.setPassword(s);
                }
            }
        });

        changePass.addValueChangeListener((e) -> {
            password.setEnabled(e.getValue());
        });

        //password.setEnabled(false);

        subTwoLayoutH.add(login, password, changePass);
        oneLayout.add(subOneLayoutH, subTwoLayoutH);
        oneLayout.setVisible(true);
        mapTabs.put(general, oneLayout);
        return general;
    }

    @Override
    protected void prepareItem(User user) {
        boolean persisted = user.getId() != 0;
        if (persisted) {
            // Find fresh entity for editing
            item = (User) itemService.getById(user.getId());
        } else {
            item = user;
            UserInfo userInfo = new UserInfo();
            user.setRole(EnumRole.ROLE_USER);
            user.setUserInfo(userInfo);
        }
        binder.setBean(item);
        setVisible(true);
    }
}
