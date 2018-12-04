package com.example.demo.views.personalview;

import com.example.demo.editors.AbstarctEditor;
import com.example.demo.editors.PersonEditorG;
import com.example.demo.entity.cars.personal.Person;
import com.example.demo.entity.organisation.Organisation;
import com.example.demo.services.ItemService;
import com.example.demo.services.PersonService;
import com.example.demo.views.AbstractGridView;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.data.selection.SelectionEvent;
import com.vaadin.flow.data.selection.SelectionListener;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import com.vaadin.ui.components.grid.Editor;

@SpringComponent
@UIScope
public class PersonalGridView extends AbstractGridView<Person> {
    public PersonalGridView(PersonService itemService, PersonEditorG editor) {
        super(itemService, editor, Person.class);
    }

    @Override
    protected Person createNewInsatnceItem() {
        return new Person();
    }

    @Override
    protected void createGrid() {
        grid = new Grid<>();
        grid.addColumn(person -> person.getId()).setHeader("ID").setResizable(true);
        grid.addColumn(person -> person.getName()).setHeader("Имя").setResizable(true);
        grid.addColumn(person -> person.getSurname()).setHeader("Фамилия").setResizable(true);
        grid.addColumn(person -> person.getBirthday()).setHeader("Дата рождения").setResizable(true);
        grid.addColumn(person -> person.getEnumTypePerson().getDisplayName()).setHeader("Тип").setResizable(true);
        add(grid);
    }
}
