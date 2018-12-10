package com.example.demo.download.excel;

import com.example.demo.download.AbstractExcelItem;
import com.example.demo.entity.organisation.EnumColumnNameForOrg;
import com.example.demo.entity.organisation.Organisation;
import com.example.demo.services.OrganisationService;
import com.vaadin.flow.spring.annotation.SpringComponent;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.List;

@SpringComponent
public class OrganisationExcelItem extends AbstractExcelItem<Organisation> {
    public OrganisationExcelItem(OrganisationService itemService) {
        super(itemService, EnumColumnNameForOrg.values());
    }

    @Override
    protected void createFirstSheet() {
        workbook.createSheet("Организации" );
    }

    @Override
    protected void inflateWorkbook(List<Organisation> list) {
        Sheet sheet = workbook.getSheetAt(0);;
        int rowNumber = 1;
        for (Organisation organisation : list) {
            System.out.println(organisation.getId());
            Row row = sheet.createRow(rowNumber);
            row.createCell(0).setCellValue(organisation.getName());
            row.createCell(1).setCellValue(organisation.getAddress());
            row.createCell(2).setCellValue(organisation.getPhone());
            row.createCell(3).setCellValue(organisation.getInn());
            row.createCell(4).setCellValue(organisation.getEgrul());
            row.createCell(5).setCellValue(organisation.getDateOfEgurl() == null ? "" : dateFormat(organisation.getDateOfEgurl()));
            row.createCell(6).setCellValue(organisation.getOkpo());
            row.createCell(7).setCellValue(organisation.getOgrn());
            row.createCell(8).setCellValue(organisation.getKpp());

            for(int i = 0; i <= 8; i++){
                sheet.autoSizeColumn(row.getCell(i).getColumnIndex());
                row.getCell(i).setCellStyle(cellStyle);
            }

            rowNumber++;
        }
    }
}
