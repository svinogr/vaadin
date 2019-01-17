package com.example.demo.upload;

import com.example.demo.services.ItemService;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.List;

public abstract class AbstractUploadExcel<T> implements Uploadable {

    private ItemService itemService;

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
}
