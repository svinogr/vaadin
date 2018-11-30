package com.example.demo.views;

import com.example.demo.IdViewable;
import com.example.demo.editors.PersonEditorG;
import com.example.demo.entity.cars.car.EnumYesNo;
import com.example.demo.entity.cars.personal.EnumColumnNamesForPerson;
import com.example.demo.entity.cars.personal.EnumTypePerson;
import com.example.demo.entity.cars.personal.Person;
import com.example.demo.services.search.*;
import com.example.demo.services.PersonService;
import com.vaadin.flow.component.AbstractField;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.HasValue;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.ConfigurableFilterDataProvider;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.selection.SelectionEvent;
import com.vaadin.flow.data.selection.SelectionListener;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@SpringComponent
@UIScope
public class PersonalView extends VerticalLayout implements IdViewable {
    public Selection selection;
    public final static String ID_VIEW = "PERSONAL_VIEW";
    private static final String SEARCH_TEXT_PLACEHOLDER = "поиск";
    private static final String ADD_BTN_TEXT = "Добавить";
    private static final String OPEN_BTN_TEXT = "Окрыть";
    private Grid<Person> grid;
    private ConfigurableFilterDataProvider<Person, Void, MyFilterItem> carVoidVoidConfigurableFilterDataProvider;
    private HorizontalLayout searchFlexLayout;
    private ComboBox<EnumColumnNamesForPerson> columnNamesComboBox;
    private ComboBox<EnumTypePerson> typePersonComboBox;
    private TextField searchField = new TextField("Строка поиска", SEARCH_TEXT_PLACEHOLDER);
    private TextField from = new TextField("От:");
    private TextField to = new TextField("До:");
    private DatePicker startDate = new DatePicker("С даты:");
    private DatePicker finishDate = new DatePicker("По дату:");
    private ComboBox<EnumYesNo> yesNOComboBox = new ComboBox("Да/Нет:");
    private ComboBox<Integer> numberComboBox = new ComboBox<>();
    private Button searchBtn;
    private Div additionalGreedMenuLayout; // лайяут для доп выбора при поиске
    private EnumColumnNamesForPerson enumColumnNameSearchSelectedForPerson = null;
    private Person selectedPerson;

    PersonService personService;
    PersonEditorG personEditor;

    public PersonalView(@Autowired PersonService personService, @Autowired PersonEditorG personEditor) {
        this.personService = personService;
        this.personEditor = personEditor;
        createSearchMenu();
        createGreedWithCars();
        createBottomMenu();
        updateListItems();
    }

    private void createBottomMenu() {
        FlexLayout flexLayout = new FlexLayout();
        Button addBtn = new Button(ADD_BTN_TEXT, VaadinIcon.PLUS.create());
        addBtn.addClickListener((event) -> {
            openEditor(new Person());
        });
        Button openBtn = new Button(OPEN_BTN_TEXT, VaadinIcon.FOLDER_OPEN.create());
        openBtn.addClickListener((event) -> {
            if (selectedPerson != null) {
                openEditor(selectedPerson);
            }
        });

        flexLayout.add(openBtn, addBtn);
        add(flexLayout);
    }

    private void createSearchMenu() {
        FlexLayout greedMenuLayout = new FlexLayout();
        FlexLayout searchLayout = new FlexLayout();
        greedMenuLayout.add(searchLayout);

        searchFlexLayout = new HorizontalLayout();
        columnNamesComboBox = new ComboBox<>();
        columnNamesComboBox.setLabel("Выбор критерия:");
        columnNamesComboBox.setWidth("100%");
        columnNamesComboBox.setPlaceholder("Поиск по:");
        columnNamesComboBox.setFilteredItems();
        columnNamesComboBox.setItems(Arrays.stream(EnumColumnNamesForPerson.values()).filter((s) ->
                s.getVisibleForCombobox()
        ));
        searchBtn = new Button(VaadinIcon.SEARCH.create());
        searchBtn.addClickListener((s) -> {
            refreshyourObjectGrid();
        });
        columnNamesComboBox.addValueChangeListener(new HasValue.ValueChangeListener<AbstractField.ComponentValueChangeEvent<ComboBox<EnumColumnNamesForPerson>, EnumColumnNamesForPerson>>() {
            @Override
            public void valueChanged(AbstractField.ComponentValueChangeEvent<ComboBox<EnumColumnNamesForPerson>, EnumColumnNamesForPerson> event) {
                additionalGreedMenuLayout.removeAll();
                if (event.getValue() != null) {
                    changeSearchFields(event);
                } else {
                    refreshyourObjectGrid();
                }

            }
        });

        additionalGreedMenuLayout = new Div();
        searchFlexLayout.add(columnNamesComboBox, additionalGreedMenuLayout, searchBtn);
        searchFlexLayout.setAlignItems(FlexComponent.Alignment.BASELINE);
        greedMenuLayout.add(searchFlexLayout);
        add(greedMenuLayout);
    }

    private MyFilterItem getItemFoeSearch(EnumColumnNamesForPerson enumColumnNamesForPerson) {
        MyFilterItem myFilterItem = null;
        switch (enumColumnNamesForPerson) {
            case DATE_OF_BIRTH:
                if (startDate.getValue() != null && finishDate != null) {
                    Date from = Date.from(
                            startDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
                    Date to = Date.from(finishDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
                    myFilterItem = new TwoDateValue(enumColumnNamesForPerson);
                    Datable twoDate = new TwoDate(from, to);
                    myFilterItem.setDatable(twoDate);
                }
                break;
            case SURNAME:
                if (searchField.getValue() != null) {
                    myFilterItem = new OneTextValue(enumColumnNamesForPerson);
                    Searchable oneTextSearch = new OneTextSearch(searchField.getValue().trim());
                    myFilterItem.setSearchable(oneTextSearch);
                }
                break;
            case TYPE_PERSON:
                if (typePersonComboBox.getValue() != null) {
                    myFilterItem = new OneTextValue(enumColumnNamesForPerson);
                    Searchable oneTextSearch = new OneTextSearch(typePersonComboBox.getValue().name());
                    myFilterItem.setSearchable(oneTextSearch);
                }
                break;
            case FIRED:
                EnumYesNo value = yesNOComboBox.getValue();
                if(value != null){
                    Checkable check = new Check(value.isYes());
                    myFilterItem = new CheckValue(enumColumnNamesForPerson);
                    myFilterItem.setCheckable(check);

                }

            default:
                System.out.println("Дефолтное значение");
        }
        return myFilterItem;

    }


    private void changeSearchFields(AbstractField.ComponentValueChangeEvent<ComboBox<EnumColumnNamesForPerson>, EnumColumnNamesForPerson> event) {
        String label = event.getValue().getDisplayName() + ":";
        searchField.setLabel(label);
        yesNOComboBox.setLabel(label);
        typePersonComboBox = new ComboBox<>();
        switch (event.getValue()) {
            case DATE_OF_BIRTH:
                additionalGreedMenuLayout.add(startDate, finishDate);
                break;
            case SURNAME:
                additionalGreedMenuLayout.add(searchField);
                break;
            case TYPE_PERSON:
                typePersonComboBox.setItems(EnumTypePerson.values());
                additionalGreedMenuLayout.add(typePersonComboBox);
                break;
            case FIRED:
                yesNOComboBox.setItems(EnumYesNo.values());
                additionalGreedMenuLayout.add(yesNOComboBox);
                break;
            default:
                System.out.println("Дефолтное значение");
        }
        searchFlexLayout.setAlignItems(FlexComponent.Alignment.BASELINE);

    }


    private void updateListItems() {
        DataProvider<Person, MyFilterItem> dataProvider = DataProvider.fromFilteringCallbacks(
                // First callback fetches items based on a query
                query -> {

                    List<Person> cars = personService.findByExample(query.getFilter(), query.getOffset(), query.getLimit());
                    return cars.stream();
                },
                // Second callback fetches the number of items for a query
                query -> personService.getCount(query.getFilter()));

        carVoidVoidConfigurableFilterDataProvider = dataProvider.withConfigurableFilter();
        grid.setDataProvider(carVoidVoidConfigurableFilterDataProvider);
    }


    private void refreshyourObjectGrid() {
        enumColumnNameSearchSelectedForPerson = columnNamesComboBox.getValue();
        MyFilterItem myFilterItem = null;

        if (enumColumnNameSearchSelectedForPerson != null) {
            myFilterItem = getItemFoeSearch(enumColumnNameSearchSelectedForPerson);
        }

        carVoidVoidConfigurableFilterDataProvider.setFilter(myFilterItem);
        grid.getDataProvider().refreshAll();
    }

    private void createGreedWithCars() {
        grid = new Grid<>();
        grid.setSelectionMode(Grid.SelectionMode.SINGLE);
        grid.addColumn(person -> person.getId()).setHeader("ID").setResizable(true);
        grid.addColumn(person -> person.getName()).setHeader("Имя").setResizable(true);
        grid.addColumn(person -> person.getSurname()).setHeader("Фамилия").setResizable(true);
        grid.addColumn(person -> person.getBirthday()).setHeader("Дата рождения").setResizable(true);
        grid.addColumn(person -> person.getEnumTypePerson().getDisplayName()).setHeader("Тип").setResizable(true);

        grid.getSelectionModel().addSelectionListener(new SelectionListener<Grid<Person>, Person>() {
            @Override
            public void selectionChange(SelectionEvent<Grid<Person>, Person> event) {
                boolean somethingSelected = !grid.getSelectedItems().isEmpty();
                if (somethingSelected) {
                    selectedPerson = event.getFirstSelectedItem().get();

                   // selection.selectItem(selectedPerson);
                 //   openEditor(event.getFirstSelectedItem().get());
                }
            }
        });

        add(grid);
    }

    @Override
    public String getIdView() {
        return ID_VIEW;
    }

    private void openEditor(Person person) {
        Dialog editorDialog = new Dialog();
        Button save = new Button("Cохранить");
        Button cancel = new Button("Отмена");
        Button delete = new Button("Удалить");
        editorDialog.add(personEditor);
        personEditor.getElement().getStyle().set("overflow", "auto");
        FlexLayout submitLayout = new FlexLayout();
        submitLayout.add(save, cancel, delete);
        save.getElement().getThemeList().add("primary");
        delete.getElement().getThemeList().add("error");
        submitLayout.setAlignItems(FlexComponent.Alignment.END);
        editorDialog.add(submitLayout);

        personEditor.setChangeHandler(() -> {
            editorDialog.close();
            refreshyourObjectGrid();
            grid.deselectAll();
            selectedPerson = null;
            //updateListItems();
        });

        cancel.addClickListener(new ComponentEventListener<ClickEvent<Button>>() {
            @Override
            public void onComponentEvent(ClickEvent<Button> event) {
                //    editorDialog.removeAll();
                editorDialog.close();
            }
        });

        save.addClickListener(event -> {
            personEditor.save();
        });

        delete.addClickListener(event -> personEditor.delete());

        personEditor.edit(person);
        personEditor.setSaveButton(save);
        editorDialog.setHeight("600px");
        editorDialog.setWidth("1200px");
        editorDialog.open();
    }


}
