package com.example.demo.editors;

import com.example.demo.dao.UserRepository;
import com.example.demo.entity.users.User;
import com.vaadin.flow.component.KeyNotifier;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationResult;
import com.vaadin.flow.data.binder.Validator;
import com.vaadin.flow.data.binder.ValueContext;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringComponent
@UIScope
public class UserEditor extends VerticalLayout implements KeyNotifier {

    private final UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * The currently edited user
     */
    private User user;

    /* Fields to edit properties in Customer entity */
    TextField login = new TextField("Логин");
    TextField password = new TextField("Пароль");
    TextField name = new TextField("Имя");
    TextField surname = new TextField("Фамилия");
    TextField patronymic = new TextField("Отчество");


    /* Action buttons */
    // TODO why more code?
    Button save = new Button("Сохранить", VaadinIcon.CHECK.create());
    Button cancel = new Button("Отмена");
    Button delete = new Button("Удалить", VaadinIcon.TRASH.create());
    HorizontalLayout actions = new HorizontalLayout(save, cancel, delete);

    Binder<User> binder = new Binder<>(User.class);

    private ChangeHandler changeHandler;

    @Autowired
    public UserEditor(UserRepository repository) {
        System.out.println("CREATE EDITOR");
        this.repository = repository;
        HorizontalLayout layout = new HorizontalLayout();
        layout.add(login, password);

        HorizontalLayout infoLayout = new HorizontalLayout();
        infoLayout.add(name, surname, patronymic);
        add(layout, infoLayout, actions);

        // bind using naming convention
        binder.bindInstanceFields(this);

        // Configure and style components
        setSpacing(true);

        save.getElement().getThemeList().add("primary");
        delete.getElement().getThemeList().add("error");

      //  addKeyPressListener(Key.ENTER, e -> save());


        // wire action buttons to save, delete and reset
        save.addClickListener(e -> save());
        delete.addClickListener(e -> delete());
        cancel.addClickListener(e -> changeHandler.onChange());
       // setVisible(false); перенес в мейнвью
    }

    void delete() {
        repository.delete(user);
        changeHandler.onChange();
    }

    void save() {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        UserInfo userInfo = user.getUserInfo();
//        if(userInfo == null){
//            userInfo = new UserInfo();
//        }
//        userInfo.setName();

        repository.save(user);
        changeHandler.onChange();
    }


    public interface ChangeHandler {
        void onChange();
    }

    public void editUser(User c) {
        if (c == null) {
            setVisible(false);
            return;
        }

        boolean persisted = c.getId() != 0;
        if (persisted) {
            // Find fresh entity for editing
            user = repository.findById(c.getId()).get();

        } else {
            user = c;
           // user.setLogin("");
        }
        //cancel.setVisible(persisted);
        cancel.setVisible(true);
        delete.setVisible(persisted);

        // Bind user properties to similarly named fields
        // Could also use annotation or "manual binding" or programmatically
        // moving values from fields to entities before saving
        binder.setBean(user);

        binder.forField(login)
                //.withValidator(new StringLengthValidator("не должен быть пустым", 1, 50))
                .withValidator(new Validator<String>() {
                    @Override
                    public ValidationResult apply(String s, ValueContext valueContext) {
                        System.out.println("-"+s+"-");
                        if(s.isEmpty()){
                            System.out.println(1);
                            save.setEnabled(false);
                            return ValidationResult.error("не должен быть пустым");
                        } else {
                            save.setEnabled(true);
                        return ValidationResult.ok();}
                    }
                })
                .bind(User::getLogin, User::setLogin);

        setVisible(true);

        // Focus first name initially
        login.focus();
    }

    public void setChangeHandler(ChangeHandler h) {
        // ChangeHandler is notified when either save or delete
        // is clicked
        changeHandler = h;
    }

}