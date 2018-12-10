package com.example.demo.download.excel;

import com.example.demo.download.AbstractExcelItem;
import com.example.demo.entity.cars.personal.EnumColumnNamesForPerson;
import com.example.demo.entity.cars.personal.Person;
import com.example.demo.services.PersonService;
import com.vaadin.flow.spring.annotation.SpringComponent;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.List;
@SpringComponent
public class PersonEcxelItem extends AbstractExcelItem<Person> {
    public PersonEcxelItem(PersonService itemService) {
        super(itemService);
    }

    @Override
    protected void createFirstSheet() {
        workbook.createSheet("Персонал");
    }

    @Override
    protected void setTittlesForSheet() {
        Sheet sheet = workbook.getSheetAt(0);
        Row row = sheet.createRow(0);
        EnumColumnNamesForPerson[] enumColumnNamesForPeople = EnumColumnNamesForPerson.values();

        for(int i = 0; i < enumColumnNamesForPeople.length; i++){
            row.createCell(i).setCellValue(enumColumnNamesForPeople[i].getDisplayName());
        }
    }

    @Override
    protected void inflateWorkbook(List<Person> list) {
        Sheet sheet = workbook.getSheetAt(0);;
        int rowNumber = 1;
        for (Person person : list) {
            Row row = sheet.createRow(rowNumber);
            row.createCell(0).setCellValue(person.getName());
            row.createCell(1).setCellValue(person.getSurname());
            row.createCell(2).setCellValue(person.getPatronymic());
            row.createCell(3).setCellValue(person.getBirthday() == null?"": person.getBirthday().toString());
            row.createCell(4).setCellValue(person.getPhone());
            row.createCell(5).setCellValue(person.getAddress());
            row.createCell(6).setCellValue(person.getComment());
            row.createCell(7).setCellValue(person.getPosition());
            row.createCell(8).setCellValue(person.getOrder());
            row.createCell(9).setCellValue(person.getDateOfOrder() == null?"":person.getDateOfOrder().toString());
            row.createCell(10).setCellValue(person.getNumberOfTabel());
            row.createCell(11).setCellValue(person.getEnumTypePerson() == null?"":person.getEnumTypePerson().toString());
            row.createCell(12).setCellValue(person.isFired()? "Уволен": "");

            for(int i = 0; i <= 12; i++){
                sheet.autoSizeColumn(row.getCell(i).getColumnIndex());
                row.getCell(i).setCellStyle(cellStyle);
            }

            rowNumber++;
        }
    }
}
