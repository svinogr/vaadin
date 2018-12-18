package com.example.demo.views.userview;

import com.example.demo.editors.AbstarctEditor;
import com.example.demo.editors.UserEditor;
import com.example.demo.entity.users.User;
import com.example.demo.services.ItemService;
import com.example.demo.services.UserService;
import com.example.demo.views.AbstractGridView;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;

@SpringComponent
@UIScope
public class UserGridView extends AbstractGridView<User> {
    public UserGridView(UserService itemService, UserEditor editor) {
        super(itemService, editor, User.class);
    }

    @Override
    protected User createNewInstanceItem() {
        return new User();
    }

    @Override
    protected void createGrid() {
        grid = new Grid<>();
        grid.addColumn(user -> user.getId()).setHeader("ID").setResizable(true);
        grid.addColumn(user -> user.getLogin()).setHeader("Логин").setResizable(true);
        grid.addColumn(user -> user.getUserInfo().getName()).setHeader("Имя").setResizable(true);
        grid.addColumn(user -> user.getUserInfo().getSurname()).setHeader("Фамилия").setResizable(true);
        grid.addColumn(user -> user.getUserInfo().getPatronymic()).setHeader("Отчество").setResizable(true);
        add(grid);
    }
}
