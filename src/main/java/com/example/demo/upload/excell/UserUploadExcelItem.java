package com.example.demo.upload.excell;

import com.example.demo.entity.users.User;
import com.example.demo.services.UserService;
import com.example.demo.upload.AbstractUploadExcel;
import com.vaadin.flow.spring.annotation.SpringComponent;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.List;

@SpringComponent
public class UserUploadExcelItem extends AbstractUploadExcel<User> {
    public UserUploadExcelItem(UserService itemService) {
        super(itemService);
    }

    @Override
    public List<User> parseWorkbook(Workbook workbook) {
        return null;
    }
}
