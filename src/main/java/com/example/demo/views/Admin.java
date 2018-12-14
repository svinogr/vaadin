package com.example.demo.views;

import com.example.demo.dao.UserRepository;
import com.example.demo.editors.UserEditor;
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

//@Route(value = "admin")
@Route(value = "login")
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
        grid.addColumn(user -> user.getUserInfo().getName()).setHeader("Имя");
        grid.addColumn(user -> user.getUserInfo().getPatronymic()).setHeader("Отчество");
        grid.addColumn(user -> user.getUserInfo().getSurname()).setHeader("Фамилия");
        updateUser();
        addBtn.addClickListener(e -> userEditor.editUser(new User()));
        HorizontalLayout actions = new HorizontalLayout();
        actions.add(addBtn);

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
