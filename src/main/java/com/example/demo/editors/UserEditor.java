package com.example.demo.editors;

import com.example.demo.entity.roles.EnumRole;
import com.example.demo.entity.users.User;
import com.example.demo.entity.users.UserInfo;
import com.example.demo.services.UserService;
import com.example.demo.validators.EmptyNullValidator;
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
    private VerticalLayout oneLayout;
    private HorizontalLayout subTwoLayoutH;
    private HorizontalLayout subEightLayoutH;
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

        oneLayout = new VerticalLayout();

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
        oneLayout.add(subOneLayoutH);

        mapTabs.put(general, oneLayout);
        return general;
    }

    protected void createLayoutWithPasswordCheck() {
        if (subTwoLayoutH != null) {
            oneLayout.remove(subTwoLayoutH);
        }

        subTwoLayoutH = new HorizontalLayout();
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
        binder.forField(password).
                bind(new ValueProvider<User, String>() {
                    @Override
                    public String apply(User user) {
                        password.setEnabled(false);
                        changePass.setVisible(true);
                        changePass.setValue(false);
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


        subTwoLayoutH.add(login, password, changePass);

        oneLayout.add(subTwoLayoutH);
        addChangedMark();

    }

    protected void addChangedMark() {
        if (subEightLayoutH != null) {
            oneLayout.remove(subEightLayoutH);
        }

        subEightLayoutH = new HorizontalLayout();

        TextField chancged = new TextField("Изменено пользователем");
        chancged.setEnabled(false);
        binder.forField(chancged)
                .bind(new ValueProvider<User, String>() {
                    @Override
                    public String apply(User user) {
                        return user.getChanged() == null ? "" : user.getChanged();
                    }
                }, new Setter<User, String>() {
                    @Override
                    public void accept(User user, String s) {

                    }
                });

        subEightLayoutH.add(chancged);

        chancged.setWidth("400px");

        oneLayout.add(subEightLayoutH);

        oneLayout.setAlignSelf(Alignment.END, subEightLayoutH);
    }

    @Override
    protected void prepareItem(User user) {
        boolean persisted = user.getId() != 0;

        if (persisted) {
            // Find fresh entity for editing
            item = (User) itemService.getById(user.getId());
            createLayoutWithPasswordCheck();
        } else {
            item = user;
            UserInfo userInfo = new UserInfo();
            user.setRole(EnumRole.ROLE_USER);
            user.setUserInfo(userInfo);
            createLayoutWithPassword();
        }

        binder.setBean(item);

        setVisible(true);
    }


    private void createLayoutWithPassword() {
        if (subTwoLayoutH != null) {
            oneLayout.remove(subTwoLayoutH);
        }

        subTwoLayoutH = new HorizontalLayout();
        subTwoLayoutH.setAlignItems(Alignment.BASELINE);

        TextField login = new TextField("Логин");
        binder.forField(login).withValidator(new EmptyNullValidator())
                .withValidationStatusHandler((s) -> {
                    setStatusComponent(login, s);
                    setEnableSubmit();
                })
                .bind(new ValueProvider<User, String>() {
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

        PasswordField password = new PasswordField("Пароль");
        binder.forField(password)
                .withValidator(new EmptyNullValidator())
                .withValidationStatusHandler((s) -> {
                    setStatusComponent(password, s);
                    setEnableSubmit();
                }).bind(new ValueProvider<User, String>() {
            @Override
            public String apply(User user) {
                return "";
            }
        }, new Setter<User, String>() {
            @Override
            public void accept(User user, String s) {
                if (!s.isEmpty()) {
                    user.setPassword(s);
                }
            }
        });


        subTwoLayoutH.add(login, password);

        oneLayout.add(subTwoLayoutH);

        addChangedMark();
    }
}
