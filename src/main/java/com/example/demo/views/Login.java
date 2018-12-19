package com.example.demo.views;

import com.example.demo.entity.roles.EnumRole;
import com.example.demo.services.LoginService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationResult;
import com.vaadin.flow.data.binder.Validator;
import com.vaadin.flow.data.binder.ValueContext;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

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

    @Autowired
    EntityManagerFactory entityManagerFactory;

    private TextField name;
    private TextField password;
    private Button login;
//    private Checkbox rememberMe;

    public Login() {
        FormLayout loginForm = new FormLayout();
        loginForm.setClassName("loginForm");
        loginForm.setWidth("300px");
     //   loginForm.setHeight("400px");
        setId("login");
        name = new TextField(" Логин", "Введите логин");
        name.setSizeFull();
        password = new TextField("Пароль", "Введите пароль");
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
