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
import com.vaadin.flow.data.binder.BindingValidationStatus;
import com.vaadin.flow.data.binder.Result;
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
    private boolean changePassBol;
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
                .asRequired("Логин не может быть пустым")
                .withValidator(new EmptyNullValidator())
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

        Checkbox changePass = new Checkbox("Изменить пароль", false);
        password = new TextField("Пароль");
        binder.forField(password)
                .asRequired("Пароль не может быть пустым")
                 .withValidator(new EmptyNullValidator())
                .withValidationStatusHandler((s)->{
                    setStatusComponent(password,s);
                    setEnableSubmit();
                })
                .bind(new ValueProvider<User, String>() {
                    @Override
                    public String apply(User user) {
                        if (user.getChanged() == null) {
                            subTwoLayoutH.remove(changePass);
                            password.setEnabled(true);
                        } else {
                            subTwoLayoutH.add(changePass);
                            password.setEnabled(false);
                            changePass.setValue(false);
                        }
                        return "";
                    }
                }, new Setter<User, String>() {
                    @Override
                    public void accept(User user, String s) {
                   if(!s.isEmpty()){
                       user.setTempField(s);
                   }
                    //    user.setTempField(s);

//                        System.out.println("jjhj  " + s);
//                        System.out.println("kkkk"+changePass.getValue());
//
//                        if(changePass.getValue()){
//                            System.out.println("--"+1);
//                            if(!s.isEmpty()){
//                                System.out.println("--"+2);
//                                user.setTempField(s);
//                            }
//                        }

//                        if (password.isEnabled()) {
//                            if (!s.isEmpty()) {
//                                System.out.println(2);
//                            user.setPassword(s);
//                            } else {
//                                user.setPassword("");
//                            }
//                        } else user.setPassword(s);
                    }
                });
//
        changePass.addValueChangeListener((e) -> {
            password.setEnabled(e.getValue());
            changePassBol = e.getValue();
            System.out.println(password.isEnabled());
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
        System.out.println(changePassBol+ "dwwdwdwdwdwdw");
        if(!changePassBol){
            item.setTempField(null);
        }
        if(item.getChanged() == null){
            System.out.println(1);
            itemService.create(item);
        } else itemService.update(item);

//        if(login != null && !password.isEmpty()) {
//
//            if (password.isEnabled() && !password.isEmpty()) {
//                item.setTempField(password.getValue());
//            } else item.setPassword(password.getValue());
//
//
//                if (item.getChanged() == null) {
//                    itemService.create(item);
//
//                } else {
//                    itemService.update(item);
//                    item.setPassword(password.getValue());
//                }
//            }
//        }

        changeHandler.onChange();
      //  item = null;
    }

    @Override
    protected void prepareItem(User user) {
        boolean persisted = user.getId() != 0;

        if (persisted) {
            // Find fresh entity for editing
            item = (User) itemService.getById(user.getId());
            // item = new User();
        /*    User d = (User) itemService.getById(user.getId());
            UserInfo userInfo = new UserInfo();
            item.setUserInfo(userInfo);
            item.setId(d.getId());
            item.setRole(d.getRole());
            item.setLogin(d.getLogin());
            item.setChanged(d.getChanged());
            item.getUserInfo().setName(d.getUserInfo().getName());
            item.getUserInfo().setSurname(d.getUserInfo().getSurname());
            item.getUserInfo().setPatronymic(d.getUserInfo().getPatronymic());*/
            //item.setPassword(null);
        } else {
            item = user;
            UserInfo userInfo = new UserInfo();
            user.setRole(EnumRole.ROLE_USER);
            user.setUserInfo(userInfo);
            user.setPassword(null);
          //  user.setLogin("");

        }
    }
}
