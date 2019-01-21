package com.example.demo.upload;

import com.example.demo.services.ItemService;
import org.apache.poi.ss.usermodel.Workbook;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public abstract class AbstractUploadExcel<T> implements Uploadable {

    private ItemService itemService;
    protected SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");


    public AbstractUploadExcel(ItemService itemService) {
        this.itemService = itemService;
    }

    @Override
    public boolean saveWorkbook(Workbook workbook) {
        List<T> list = parseWorkbook(workbook);
        boolean saved = itemService.saveList(list);
        return saved;
    }

    public abstract List<T> parseWorkbook(Workbook workbook);

    protected Date dateFormat(String date) throws ParseException {
        return format.parse(date);
    }
}
