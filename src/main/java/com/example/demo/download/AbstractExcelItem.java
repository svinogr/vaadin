package com.example.demo.download;

import com.example.demo.services.ItemService;
import com.example.demo.services.search.MyFilterItem;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
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
        Row row = sheet.createRow(1);
        Cell cell = row.createCell(1);
cell.setCellValue("размер" + list.size());
        return w;
    }

    @Override
    public byte[] getBytesByFilterItem(MyFilterItem myFilterItem) {
        Optional<MyFilterItem> myFilterItemOptional;
        if(myFilterItem == null){
            myFilterItemOptional = Optional.ofNullable(myFilterItem);
        }else {
            myFilterItemOptional = Optional.of(myFilterItem);
        }

       List<T> list = itemService.findByExampleWithoutPagable(myFilterItemOptional);
        System.out.println(list.size() +"кгига");
        Workbook workbook = createXLS(list);
        return ((HSSFWorkbook) workbook).getBytes();
    }
}
