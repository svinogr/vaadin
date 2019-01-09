package com.example.demo.download.excel;

import com.example.demo.download.AbstractExcelItem;
import com.example.demo.entity.users.EnumUserColumnNameForUser;
import com.example.demo.entity.users.User;
import com.example.demo.services.UserService;
import com.vaadin.flow.spring.annotation.SpringComponent;

import java.util.List;

@SpringComponent
public class UserExcelItem extends AbstractExcelItem<User> {

    public UserExcelItem(UserService itemService) {
        super(itemService, EnumUserColumnNameForUser.values());
    }

    @Override
    protected void createFirstSheet() {
    }

    @Override
    protected void inflateWorkbook(List<User> list) {
    }
}
