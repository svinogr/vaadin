package com.example.demo.download.excel;

import com.example.demo.download.AbstractExcelItem;
import com.example.demo.entity.cars.personal.Person;
import com.example.demo.services.ItemService;
import com.example.demo.services.PersonService;
import com.vaadin.flow.spring.annotation.SpringComponent;

@SpringComponent
public class PersonEcxelItem extends AbstractExcelItem<Person> {
    public PersonEcxelItem(PersonService itemService) {
        super(itemService);
    }
}
