package com.example.demo.download.excel;

import com.example.demo.download.AbstractExcelItem;
import com.example.demo.entity.jornal.EnumColumnNameForJournal;
import com.example.demo.entity.jornal.JournalItem;
import com.example.demo.services.JournalService;
import com.vaadin.flow.spring.annotation.SpringComponent;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.List;
@SpringComponent
public class JournalExcelItem extends AbstractExcelItem<JournalItem> {
    public JournalExcelItem(JournalService itemService) {
        super(itemService, EnumColumnNameForJournal.values());
    }


    @Override
    protected void createFirstSheet() {
        workbook.createSheet("Журнал техники");

    }

    @Override
    protected void inflateWorkbook(List<JournalItem> list) {
        Sheet sheet = workbook.getSheetAt(0);;
        int rowNumber = 1;
        for (JournalItem journalItem : list) {
            System.out.println(journalItem.getId());
            Row row = sheet.createRow(rowNumber);
            row.createCell(0).setCellValue(journalItem.getEnumTypeRecord() == null?"": journalItem.getEnumTypeRecord().toString());
            row.createCell(1).setCellValue(journalItem.getTypeTo() == null? "": journalItem.getTypeTo().toString());
              row.createCell(2).setCellValue(journalItem.getTypeOil() == null? "": journalItem.getTypeTo().toString());
              row.createCell(3).setCellValue(journalItem.getName());
              row.createCell(4).setCellValue(journalItem.getModel());
              row.createCell(5).setCellValue(journalItem.getCode());
            row.createCell(6).setCellValue(journalItem.getDateSetup() == null ? "" : dateFormat(journalItem.getDateSetup()));
              row.createCell(7).setCellValue(journalItem.getSetupMileage());
              row.createCell(8).setCellValue(journalItem.getCost() == null?"":journalItem.getCost().toString());
              row.createCell(9).setCellValue(journalItem.getQuantity());
              row.createCell(10).setCellValue(journalItem.getTypeOfUnits());
              row.createCell(11).setCellValue(journalItem.getComment());
            row.createCell(12).setCellValue(journalItem.getDatedelete() == null ? "" : dateFormat(journalItem.getDatedelete()));
              row.createCell(13).setCellValue(journalItem.getDeleteMileage());
              row.createCell(14).setCellValue(journalItem.getCause());
              row.createCell(15).setCellValue(journalItem.isClosed()? "Закрыт":"");

            for(int i = 0; i <=15; i++){
                sheet.autoSizeColumn(row.getCell(i).getColumnIndex());
                row.getCell(i).setCellStyle(cellStyle);
            }

            rowNumber++;
        }
    }
}
