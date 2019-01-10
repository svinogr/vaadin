package com.example.demo.editors;

import com.example.demo.entity.roles.EnumRole;
import com.example.demo.entity.users.User;
import com.example.demo.entity.users.UserInfo;
import com.example.demo.services.UniqTestInterface;
import com.example.demo.services.UserService;
import com.example.demo.validators.EmptyNullOrCheckableValidator;
import com.example.demo.validators.EmptyNullValidator;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
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
    private TextField password;
    private TextField login;
    private Checkbox changePass;

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
        createLayoutWithPasswordCheck();
        mapTabs.put(general, oneLayout);
        return general;
    }

    protected void createLayoutWithPasswordCheck() {
        if (subTwoLayoutH != null) {
            oneLayout.remove(subTwoLayoutH);
        }

        subTwoLayoutH = new HorizontalLayout();
        subTwoLayoutH.setAlignItems(Alignment.BASELINE);

        login = new TextField("Логин");
        binder.forField(login)
                //   .withValidator(new EmptyNullValidator())
                .withValidator(new EmptyNullValidator())
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

        changePass = new Checkbox("Изменить пароль", false);
        password = new TextField("Пароль");
        binder.forField(password)
                //.asRequired()
                .withValidator(new EmptyNullOrCheckableValidator(changePass))
                .bind(new ValueProvider<User, String>() {
                    @Override
                    public String apply(User user) {
                        if (user.getChanged() == null) {
                            subTwoLayoutH.remove(changePass);
                            password.setEnabled(true);
                            changePass.setValue(true);
                        } else {
                            subTwoLayoutH.add(changePass);
                            password.setEnabled(false);
                            changePass.setValue(false);
                        }
                        return null;
                    }
                }, new Setter<User, String>() {
                    @Override
                    public void accept(User user, String s) {
                        user.setTempField(s);

                    }
                });

        changePass.addValueChangeListener((e) -> {
            password.setEnabled(e.getValue());

        });

        subTwoLayoutH.add(login, password, changePass);

        oneLayout.add(subTwoLayoutH);
        addChangedMark();
    }

    protected void setStatusComponent(Component component, boolean status) {
        if (component instanceof TextField) {
            TextField textField = ((TextField) component);
            textFieldsList.add(textField);
            if (status) {
                //textField.setErrorMessage();
                textField.setInvalid(true);
            } else {
                textField.setInvalid(false);
            }
        }
    }

    protected void addChangedMark() {
        if (subEightLayoutH != null) {
            oneLayout.remove(subEightLayoutH);
        }

        subEightLayoutH = new HorizontalLayout();

        TextField changed = new TextField("Изменено пользователем");
        changed.setEnabled(false);
        binder.forField(changed)
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

        subEightLayoutH.add(changed);

        changed.setWidth("400px");

        oneLayout.add(subEightLayoutH);
        oneLayout.setAlignSelf(Alignment.END, subEightLayoutH);
    }

    @Override
    public void save() {
        binder.validate();
        if (!binder.isValid()) {
            return;
        }

        UniqTestInterface uniqTestInterface = (UniqTestInterface) itemService;
        if (!uniqTestInterface.isUniq(item.getLogin(), item.getId())) {
            login.setInvalid(true);
            login.setErrorMessage("Такой логин уже существует");
            return;
        } else login.setInvalid(false);

        String text = null;
        User user = null;

        if (item.getChanged() == null) {
            user = (User) itemService.create(item);
            if (user == null) {
                text = "Невозможно сохранить. Проверьте сохраняемы данные";
            } else text = "Пользователь добавлен";
        } else {
            if (!password.isEnabled()) {
                item.setTempField(null);
            }
            user = (User) itemService.update(item);
            if (user == null) {
                text = "Невозможно сохранить. Проверьте сохраняемы данные";
            } else text = "Пользователь изменен";
        }

        notification(text);
        changeHandler.onChange();
    }

    private void notification(String text) {
        Notification notification = new Notification(
                text, 3000,
                Notification.Position.TOP_START);
        notification.open();
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
    }
}
