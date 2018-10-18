package com.example.demo;

import com.example.demo.dao.UserRepository;
import com.example.demo.entity.users.User;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.selection.SelectionEvent;
import com.vaadin.flow.data.selection.SelectionListener;
import com.vaadin.flow.router.Route;

import java.util.List;

@Route(value = "admin")
public class Admin extends VerticalLayout {

    private UserRepository userRepository;
    private UserEditor userEditor;
    private Button addBtn;
    private Grid<User> grid;

    public Admin(UserRepository userRepository, UserEditor userEditor) {

        this.userRepository = userRepository;
        this.userEditor = userEditor;
        addBtn = new Button("Добавить нового пользователя", VaadinIcon.PLUS.create());

        userEditor.setVisible(false);
        grid = new Grid<>();
        grid.addColumn(User::getLogin).setHeader("Логин");
        grid.addColumn(User::getPassword).setHeader("Пароль");
        grid.addColumn(User::getRole)
                .setHeader("Роль");
        updateUser();
        addBtn.addClickListener(e -> userEditor.editUser(new User()));
        HorizontalLayout actions = new HorizontalLayout();
        actions.add(addBtn);

        // Connect selected Customer to editor or hide if none is selected
//        grid.asSingleSelect().addValueChangeListener(e -> {
//            userEditor.editUser(e.getValue());
//        });
        grid.getSelectionModel().addSelectionListener(new SelectionListener<Grid<User>, User>() {
            @Override
            public void selectionChange(SelectionEvent<Grid<User>, User> event) {
                boolean somethingSelected = !grid.getSelectedItems().isEmpty();
                if(somethingSelected){
                    userEditor.editUser(event.getFirstSelectedItem().get());
                }
            }
        });

        userEditor.setChangeHandler(() -> {
            userEditor.setVisible(false);
            updateUser();
        });

        add(actions, grid, userEditor);

    }

    private void updateUser() {
        List<User> userList = userRepository.findAll();
        grid.setItems(userList);
    }


}
