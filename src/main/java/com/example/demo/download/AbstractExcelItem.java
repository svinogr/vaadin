package com.example.demo.download;

import com.example.demo.services.ItemService;
import com.example.demo.services.search.MyFilterItem;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public abstract class AbstractExcelItem<T> implements Downloadedable {
    private ItemService itemService;

    public AbstractExcelItem(@Autowired ItemService<T> itemService) {
        this.itemService = itemService;
    }

    protected Workbook createXLS(List<T> list){
        Workbook w = new  HSSFWorkbook();
        Sheet sheet = w.createSheet();
        sheet.createRow(1);
        return w;
    }

    @Override
    public byte[] getBytesByFilterItem(MyFilterItem myFilterItem) {
        System.out.println(myFilterItem);
       List<T> list = itemService.findByExampleWithoutPagable(Optional.of(myFilterItem));
       Workbook workbook = createXLS(list);
        return ((HSSFWorkbook) workbook).getBytes();
    }
}
