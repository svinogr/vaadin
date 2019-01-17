package com.example.demo.upload.excell;

import com.example.demo.entity.cars.personal.Person;
import com.example.demo.services.PersonService;
import com.example.demo.upload.AbstractUploadExcel;
import com.vaadin.flow.spring.annotation.SpringComponent;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.List;

@SpringComponent
public class PersonUploadExcelItem extends AbstractUploadExcel<Person> {
    public PersonUploadExcelItem(PersonService itemService) {
        super(itemService);
    }

    @Override
    public List<Person> parseWorkbook(Workbook workbook) {
        return null;
    }
}
