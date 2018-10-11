package com.example.demo;

import com.example.demo.entity.users.User;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationResult;
import com.vaadin.flow.data.binder.Validator;
import com.vaadin.flow.data.binder.ValueContext;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.persistence.EntityManagerFactory;
import java.util.Collection;

@HtmlImport("styles/styles.html")
@Route(value = "login")
public class Login extends VerticalLayout {

    //@Autowired
  //  private VaadinSharedSecurity vaadinSharedSecurity;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    EntityManagerFactory entityManagerFactory;
//    @Autowired
//    SessionFactory sessionFactory;

    private TextField name;
    private TextField password;
    private Button login, logout;
    private Checkbox rememberMe;

    public Login() {
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
        submitLayout.add(login, logout, rememberMe);
        submitLayout.setAlignItems(Alignment.CENTER);

        loginLayout.setAlignItems(Alignment.CENTER);
        loginLayout.add(name, password);

        loginForm.add(loginLayout, submitLayout);

        login.addClickListener(event -> submitLogin());
        logout.addClickListener(event -> submitLogout());

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

    private void submitLogout() {
      SecurityContextHolder.getContext().getAuthentication().setAuthenticated(false);
        this.getUI().ifPresent(ui -> ui.navigate("login"));
    }

    private void submitLogin() {
//        User user = entityManagerFactory.createEntityManager().find(User.class, (long) 1);
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(name.getValue(),
                password.getValue());
        try {
            Authentication auth = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

            SecurityContext sc = SecurityContextHolder.getContext();
            sc.setAuthentication(auth);

            String rout = getRout();
            this.getUI().ifPresent(ui -> ui.navigate(rout));
        }catch (BadCredentialsException e){
            name.focus();
            //name.seC("Неправильный пароль или логин");
        }
    }

    private String getRout() {
        String rout = "login";
        Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        for (GrantedAuthority role : authorities) {
            if (role.getAuthority().equals("ROLE_ADMIN") && rememberMe.getValue()) {
                rout =  "admin";
            } else if (role.getAuthority().equals("ROLE_USER") || role.getAuthority().equals("ROLE_ADMIN") ) {
               rout =  "main";
            }
        }
        return rout;
    }
}
