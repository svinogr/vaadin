package com.example.demo.download.excel;

import com.example.demo.download.AbstractExcelItem;
import com.example.demo.entity.cars.personal.Person;
import com.example.demo.services.ItemService;
import com.example.demo.services.PersonService;
import com.vaadin.flow.spring.annotation.SpringComponent;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.List;

//@SpringComponent
public class PersonEcxelItem extends AbstractExcelItem<Person> {
    public PersonEcxelItem(PersonService itemService) {
        super(itemService);
    }

    @Override
    protected void createFirstSheet() {

    }

    @Override
    protected void setTittlesForSheet() {

    }

    @Override
    protected void inflateWorkbook(List<Person> list) {

    }
}
