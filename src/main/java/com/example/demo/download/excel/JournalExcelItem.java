package com.example.demo.download.excel;

import com.example.demo.download.AbstractExcelItem;
import com.example.demo.entity.cars.car.EnumColumnNamesForCar;
import com.example.demo.entity.jornal.EnumColumnNameForJournal;
import com.example.demo.entity.jornal.JournalItem;
import com.example.demo.services.ItemService;
import com.example.demo.services.JournalService;
import com.vaadin.flow.spring.annotation.SpringComponent;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.List;
@SpringComponent
public class JournalExcelItem extends AbstractExcelItem<JournalItem> {
    public JournalExcelItem(JournalService itemService) {
        super(itemService);
    }


    @Override
    protected void createFirstSheet() {
        workbook.createSheet("Журнал техники");

    }

    @Override
    protected void setTittlesForSheet() {
        Sheet sheet = workbook.getSheetAt(0);
        Row row = sheet.createRow(0);
        EnumColumnNamesForCar[] valueColumnNamesForCarss = EnumColumnNamesForCar.values();

        for(int i = 0; i < valueColumnNamesForCarss.length; i++){
            row.createCell(i).setCellValue(valueColumnNamesForCarss[i].getDisplayName());
        }


        Sheet sheet2 = workbook.createSheet("Журнал");
        Row row2 = sheet2.createRow(0);
        EnumColumnNameForJournal[] columnNameForJournals = EnumColumnNameForJournal.values();

        for(int i = 0; i < columnNameForJournals.length; i++){
            row2.createCell(i).setCellValue(valueColumnNamesForCarss[i].getDisplayName());
        }

    }

    @Override
    protected void inflateWorkbook(List<JournalItem> list) {
        Sheet sheet = workbook.getSheetAt(1);
        int rowNumber = 1;
        for (JournalItem journalItem : list) {
            Row row = sheet.createRow(rowNumber);

            //  row.createCell(0).setCellValue(car.getGeneralData().getDateOfTakeToBalanse() == null?"":car.getGeneralData().getDateOfTakeToBalanse().toString());
            // row.createCell(1).setCellValue(car.getGeneralData().isDecommissioned()?"Да":"Нет");
            rowNumber++;
        }
    }
}
