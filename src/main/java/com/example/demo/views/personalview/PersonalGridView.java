package com.example.demo.views.personalview;

import com.example.demo.editors.PersonEditor;
import com.example.demo.entity.cars.personal.Person;
import com.example.demo.services.PersonService;
import com.example.demo.views.AbstractGridView;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;

@SpringComponent
@UIScope
public class PersonalGridView extends AbstractGridView<Person> {
    public PersonalGridView(PersonService itemService, PersonEditor editor) {
        super(itemService, editor, Person.class);
    }

    @Override
    protected Person createNewInstanceItem() {
        return new Person();
    }

    @Override
    protected void createGrid() {
        grid = new Grid<>();
        grid.addColumn(person -> person.getEnumTypePerson().getDisplayName()).setHeader("Тип").setResizable(true);
        grid.addColumn(person -> person.getName()).setHeader("Имя").setResizable(true);
        grid.addColumn(person -> person.getSurname()).setHeader("Фамилия").setResizable(true);
        grid.addColumn(person -> person.getBirthday() == null ? "" : dateFormat(person.getBirthday())).setHeader("Дата рождения").setResizable(true);
        grid.addColumn(person -> person.getPhone()).setHeader("Телефон").setResizable(true);
        grid.addColumn(person -> person.getCabinet()).setHeader("Кабинет").setResizable(true);
        add(grid);
    }
}
