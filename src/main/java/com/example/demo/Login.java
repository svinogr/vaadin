package com.example.demo;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@HtmlImport("styles/styles.html")
@Route(value = "log1n")
public class Login extends VerticalLayout {

    private TextField name;
    private TextField password;
    private Button submit;

    public Login() {
        VerticalLayout loginLayout = new VerticalLayout();
        loginLayout.setClassName("login-in");
        name = new TextField(" Логин", "Введите логин");
        password = new TextField("Пароль", "Введите пароль");
        submit = new Button("Войти");
        loginLayout.setAlignItems(Alignment.CENTER);
        loginLayout.add(name, password, submit);

        submit.addClickListener( event -> submitLogin());

        setAlignItems(Alignment.BASELINE);
        add(loginLayout);
    }

    private void submitLogin() {
        System.out.println(name.getValue());
    }
}
