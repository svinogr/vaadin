package com.example.demo.editors;

import com.example.demo.entity.users.User;
import com.example.demo.services.ItemService;
import com.example.demo.services.UserService;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;

@SpringComponent
@UIScope
public class UserEditor extends AbstarctEditor<User> {
    public UserEditor(UserService itemService) {
        super(itemService);
    }

    @Override
    void setTitle() {

    }

    @Override
    void createTabs(Tabs tabs) {

    }

    @Override
    protected void prepareItem(User item) {

    }
}
