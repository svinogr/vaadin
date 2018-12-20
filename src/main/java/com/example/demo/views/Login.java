package com.example.demo.views;

import com.example.demo.entity.roles.EnumRole;
import com.example.demo.services.LoginService;
import com.example.demo.services.UserService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationResult;
import com.vaadin.flow.data.binder.Validator;
import com.vaadin.flow.data.binder.ValueContext;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManagerFactory;

@HtmlImport("styles/styles.html")
@Route(value = "login")
@StyleSheet(value = "styles/style.css")
public class Login extends VerticalLayout {
    private final static String MAIN_ROUT = "main";
    private final static String ADMIN_ROUT = "admin";
    private final static String LOGIN_ROUT = "login";

    @Autowired
    LoginService loginService;

    UserService userService;

    @Autowired
    EntityManagerFactory entityManagerFactory;

    private TextField name;
    private PasswordField password;
    private Button login;
//    private Checkbox rememberMe;

    public Login(@Autowired UserService userService) {
        this.userService = userService;
        createAdminUser();
        FormLayout loginForm = new FormLayout();
        loginForm.setClassName("loginForm");
        loginForm.setWidth("300px");
        setId("login");
        name = new TextField(" Логин", "Введите логин");
        name.setSizeFull();
        password = new PasswordField("Пароль", "Введите пароль");
        password.setSizeFull();
        login = new Button("Войти");
        login.setClassName("loginSubmit");
        login.setSizeFull();

        loginForm.add(name, password, login);

        login.addClickListener(event -> submitLogin());

        add(loginForm);
        setAlignSelf(Alignment.CENTER, loginForm);

        Validator<String> validator = new Validator<String>() {
            @Override
            public ValidationResult apply(String s, ValueContext valueContext) {
                return ValidationResult.error("неправильный пароль или логин");
            }
        };

        new Binder<String>().forField(name).withValidator(validator);
    }

    private void createAdminUser() {
        userService.createDefaultUserAdmin();
    }

    private void submitLogin() {
        EnumRole role = loginService.login(name.getValue(), password.getValue());

        String rout;
        switch (role){
            case ROLE_USER: rout = MAIN_ROUT;
            break;
            case ROLE_ADMIN: rout = MAIN_ROUT;
            break;
            default: rout = LOGIN_ROUT;
        }
        this.getUI().ifPresent(ui -> ui.navigate(rout));

    }

}
