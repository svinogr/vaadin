package com.example.demo.upload;

import com.example.demo.services.ItemService;
import com.example.demo.services.LoginService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public abstract class AbstractUploadExcel<T> implements Uploadable<T> {

    private ItemService itemService;
    protected SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
    @Autowired
    protected LoginService loginService;

    public AbstractUploadExcel(ItemService itemService) {
        this.itemService = itemService;
    }

    @Override
    public List<T> saveWorkbook(Workbook workbook) {
        List<T> list = parseWorkbook(workbook);
        list = itemService.saveList(list);
        return list;
    }

    public abstract List<T> parseWorkbook(Workbook workbook);

    protected Date dateFormat(String date) throws ParseException {
        return format.parse(date);
    }
}
