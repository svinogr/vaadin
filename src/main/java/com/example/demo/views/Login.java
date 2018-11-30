package com.example.demo.views;

import com.example.demo.entity.roles.EnumRole;
import com.example.demo.services.LoginService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Label;
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
//@Route(value = "login")
public class Login extends VerticalLayout {

    private final static String MAIN_ROUT = "main";
    private final static String ADMIN_ROUT = "admin";
    private final static String LOGIN_ROUT = "login";

    //@Autowired
  //  private VaadinSharedSecurity vaadinSharedSecurity;

    @Autowired
    LoginService loginService;

    @Autowired
    EntityManagerFactory entityManagerFactory;
//    @Autowired
//    SessionFactory sessionFactory;

    private TextField name;
    private TextField password;
    private Button login, logout;
    private Checkbox rememberMe;

    public Login() {
        //submitLogout();
        FormLayout loginForm = new FormLayout();
        loginForm.setSizeUndefined();

        VerticalLayout loginLayout = new VerticalLayout();
        loginLayout.setClassName("login-in");

        Label label = new Label(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
        name = new TextField(" Логин", "Введите логин");
        password = new TextField("Пароль", "Введите пароль");
        rememberMe = new Checkbox("как админ");
        login = new Button("Войти");
        logout = new Button("Выйти");

        VerticalLayout submitLayout = new VerticalLayout();
        submitLayout.add(login, rememberMe);
        submitLayout.setAlignItems(Alignment.CENTER);

        loginLayout.setAlignItems(Alignment.CENTER);
        loginLayout.add(name, password);

        loginForm.add(loginLayout, submitLayout);

        login.addClickListener(event -> submitLogin());
       // logout.addClickListener(event -> submitLogout());

        setAlignItems(Alignment.CENTER);
        add(loginForm, label);
        Validator<String> validator = new Validator<String>() {
            @Override
            public ValidationResult apply(String s, ValueContext valueContext) {
                return ValidationResult.error("неправильный пароль или логин");
            }
        };


        new Binder<String>().forField(name).withValidator(validator);
    }

 /*   private void submitLogout() {

      SecurityContextHolder.getContext().getAuthentication().setAuthenticated(false);
        this.getUI().ifPresent(ui -> ui.navigate(LOGIN_ROUT));
    }
*/
    private void submitLogin() {
        EnumRole role = loginService.login(name.getValue(), password.getValue(), rememberMe.getValue());

        String rout;
        switch (role){
            case ROLE_USER: rout = MAIN_ROUT;
            break;
            case ROLE_ADMIN: rout = ADMIN_ROUT;
            break;
            default: rout = LOGIN_ROUT;
        }
        this.getUI().ifPresent(ui -> ui.navigate(rout));

    }

}
