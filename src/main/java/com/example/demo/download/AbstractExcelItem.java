package com.example.demo.download;

import com.example.demo.services.ItemService;
import com.example.demo.services.search.MyFilterItem;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public abstract class AbstractExcelItem<T> implements Downloadedable {
    private ItemService itemService;
    protected Workbook workbook;
    protected HSSFCellStyle cellStyleTitle;
    protected HSSFCellStyle cellStyle;
    protected HSSFCellStyle cellStyleDate;

    public AbstractExcelItem(@Autowired ItemService<T> itemService) {
        this.itemService = itemService;

    }

    protected abstract void createFirstSheet();

    protected abstract void setTittlesForSheet();


    @Override
    public byte[] getBytesByFilterItem(MyFilterItem myFilterItem) {
        workbook = new HSSFWorkbook();
        createFirstSheet();
        setTittlesForSheet();
        setupStyle();

        Optional<MyFilterItem> myFilterItemOptional;

        if(myFilterItem == null){
            myFilterItemOptional = Optional.ofNullable(myFilterItem);
        }else {
            myFilterItemOptional = Optional.of(myFilterItem);
        }

        List<T> list = itemService.findByExampleWithoutPagable(myFilterItemOptional);

        inflateWorkbook(list);

        return ((HSSFWorkbook) workbook).getBytes();
    }

    protected abstract void inflateWorkbook(List<T> list);

    protected void setupStyle() {
        HSSFFont font = (HSSFFont) workbook.createFont();
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

        cellStyleTitle = (HSSFCellStyle) workbook.createCellStyle();
        cellStyleTitle.setFont(font);
        cellStyleTitle.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);
        cellStyleTitle.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);
        cellStyleTitle.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);
        cellStyleTitle.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);

        cellStyle = (HSSFCellStyle) workbook.createCellStyle();
        cellStyle.setFont(font);
        cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        cellStyle.setWrapText(true);

        cellStyleDate = (HSSFCellStyle) workbook.createCellStyle();
        cellStyleDate.setFont(font);
        cellStyleDate.setBorderTop(HSSFCellStyle.BORDER_THIN);
        cellStyleDate.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        cellStyleDate.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        cellStyleDate.setBorderRight(HSSFCellStyle.BORDER_THIN);
        cellStyleDate.setWrapText(true);
        Sheet sheet = workbook.getSheetAt(0);
        Row row = sheet.getRow(0);

        for(int i = 0 ; i < row.getPhysicalNumberOfCells(); i++){
            row.getCell(i).setCellStyle(cellStyleTitle);
            sheet.autoSizeColumn(i);
        }



    }
}
