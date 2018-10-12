package com.example.demo;

import com.example.demo.dao.UserRepository;
import com.example.demo.entity.users.User;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyNotifier;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;

@SpringComponent
@UIScope
public class UserEditor extends VerticalLayout implements KeyNotifier {

    private final UserRepository repository;

    /**
     * The currently edited user
     */
    private User user;

    /* Fields to edit properties in Customer entity */
    TextField login = new TextField("Логин");
    TextField password = new TextField("Пароль");

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
        this.repository = repository;

        add(login, password, actions);

        // bind using naming convention
        binder.bindInstanceFields(this);

        // Configure and style components
        setSpacing(true);

        save.getElement().getThemeList().add("primary");
        delete.getElement().getThemeList().add("error");

        addKeyPressListener(Key.ENTER, e -> save());

        // wire action buttons to save, delete and reset
        save.addClickListener(e -> save());
        delete.addClickListener(e -> delete());
        cancel.addClickListener(e -> changeHandler.onChange());
        setVisible(false);
    }

    void delete() {
        repository.delete(user);
        changeHandler.onChange();
    }

    void save() {
        repository.save(user);
        changeHandler.onChange();
    }


    public interface ChangeHandler {
        void onChange();
    }

    public final void editUser(User c) {
        if (c == null) {
            setVisible(false);
            return;
        }
        final boolean persisted = c.getId() != 0;
        if (persisted) {
            // Find fresh entity for editing
            user = repository.findById(c.getId()).get();

        }
        else {
            user = c;
        }
        //cancel.setVisible(persisted);
        cancel.setVisible(true);
        delete.setVisible(persisted);

        // Bind user properties to similarly named fields
        // Could also use annotation or "manual binding" or programmatically
        // moving values from fields to entities before saving
        binder.setBean(user);

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