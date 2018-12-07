package com.example.demo.download;

import com.example.demo.services.ItemService;
import com.example.demo.services.search.MyFilterItem;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import static org.apache.poi.hssf.model.InternalSheet.createSheet;

public abstract class AbstractExcelItem<T> implements Downloadedable {
    private ItemService itemService;
    protected Workbook workbook;

    public AbstractExcelItem(@Autowired ItemService<T> itemService) {
        this.itemService = itemService;
        workbook = new HSSFWorkbook();
        createFirstSheet();
        setTittlesForSheet();
        setupStyle();
    }

    protected abstract void createFirstSheet();

    protected abstract void setTittlesForSheet();


    @Override
    public byte[] getBytesByFilterItem(MyFilterItem myFilterItem) {
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
        HSSFCellStyle cellStyle = (HSSFCellStyle) workbook.createCellStyle();
        cellStyle.setFont(font);

        Sheet sheet = workbook.getSheetAt(0);
        Row row = sheet.getRow(0);

        for(int i =0 ; i < row.getPhysicalNumberOfCells(); i++){
            row.getCell(i).setCellStyle(cellStyle);
        }

    }
}
