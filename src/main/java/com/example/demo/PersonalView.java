package com.example.demo;

import com.example.demo.entity.cars.car.EnumColumnNamesForCar;
import com.example.demo.entity.cars.car.EnumYesNo;
import com.example.demo.entity.cars.personal.EnumColumnNamesForPerson;
import com.example.demo.entity.cars.personal.EnumTypePerson;
import com.example.demo.entity.cars.personal.Person;
import com.example.demo.entity.cars.utils.search.Datable;
import com.example.demo.entity.cars.utils.search.MyFilterItem;
import com.example.demo.entity.cars.utils.search.TwoDate;
import com.example.demo.entity.cars.utils.search.TwoDateValue;
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
import org.springframework.beans.factory.annotation.Autowired;

import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class PersonalView extends VerticalLayout {
    public CarView.Selection selection;
    public final static String ID_VIEW = "CAR_VIEW";
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
    private EnumColumnNamesForCar enumColumnNameSearchSelectedForCar = null;
    private Person selectedPerson;

    PersonService personService;
    PersonEditor personEditor;

    public PersonalView(@Autowired PersonService personService, @Autowired PersonEditor personEditor) {
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
           // openEditor(new Person());
        });
        Button openBtn = new Button(OPEN_BTN_TEXT, VaadinIcon.FOLDER_OPEN.create());
        openBtn.addClickListener((event) -> {
            if (selectedPerson != null) {
              //  openEditor(selectedPerson);
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
            case DECOMISSIONED:
                EnumYesNo value = yesNOComboBox.getValue();
                if (value != null) {
                    Checkable check = new Check(value.isYes());
                    myFilterItem = new CheckValue(enumColumnNamesForPerson);
                    myFilterItem.setCheckable(check);
                }

                break;
            case DATE_OF_COMMISSIONED:
                if (startDate.getValue() != null && finishDate != null) {
                    Date from = Date.from(
                            startDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
                    Date to = Date.from(finishDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
                    myFilterItem = new TwoDateValue(enumColumnNamesForPerson);
                    Datable twoDate = new TwoDate(from, to);
                    myFilterItem.setDatable(twoDate);
                }

                break;
            case FAULY:
                if (yesNOComboBox.getValue() != null) {
                    myFilterItem = new CheckValue(enumColumnNamesForPerson);
                    Checkable check = new Check(yesNOComboBox.getValue().isYes());
                    myFilterItem.setCheckable(check);
                }
                break;
            case PODRAZDELENIE_OR_GARAGE:
                if (searchField.getValue() != null) {
                    myFilterItem = new OneTextValue(enumColumnNamesForPerson);
                    Searchable oneTextSearch = new OneTextSearch(searchField.getValue().trim());
                    myFilterItem.setSearchable(oneTextSearch);
                }
                break;
            case COLONNA:
                if (searchField.getValue() != null) {
                    myFilterItem = new OneTextValue(enumColumnNamesForPerson);
                    Searchable oneTextSearch = new OneTextSearch(searchField.getValue().trim());
                    myFilterItem.setSearchable(oneTextSearch);
                }
                break;
            case NUMBER_OF_GARAGE:
                if (searchField.getValue() != null) {
                    myFilterItem = new OneTextValue(enumColumnNamesForPerson);
                    Searchable oneTextSearch = new OneTextSearch(searchField.getValue().trim());
                    myFilterItem.setSearchable(oneTextSearch);
                }
                break;
            case NUMBER_OF_INVENTAR:
                if (searchField.getValue() != null) {
                    myFilterItem = new OneTextValue(enumColumnNamesForPerson);
                    Searchable oneTextSearch = new OneTextSearch(searchField.getValue().trim());
                    myFilterItem.setSearchable(oneTextSearch);
                }
                break;
            case TYPE_OF_FUEL:
                if (typeFuelComboBox.getValue() != null) {
                    myFilterItem = new OneTextValue(enumColumnNamesForPerson);
                    Searchable oneTextSearch = new OneTextSearch(typeFuelComboBox.getValue().name());
                    myFilterItem.setSearchable(oneTextSearch);
                }
                break;
            case MILEAGE:
                if (from.getValue() != null && to.getValue() != null) {
                    myFilterItem = new TwoDateValue(enumColumnNamesForPerson);
                    Searchable searchable = new TwoTextSearch(from.getValue().trim(), to.getValue().trim());
                    myFilterItem.setSearchable(searchable);
                }
                break;
            case MASHINE_HOURS:
                if (from.getValue() != null && to.getValue() != null) {
                    myFilterItem = new TwoDateValue(enumColumnNamesForPerson);
                    Searchable searchable = new TwoTextSearch(from.getValue().trim(), to.getValue().trim());
                    myFilterItem.setSearchable(searchable);
                }
                break;
            case VIN:
                if (searchField.getValue() != null) {
                    myFilterItem = new OneTextValue(enumColumnNamesForPerson);
                    Searchable oneTextSearch = new OneTextSearch(searchField.getValue().trim());
                    myFilterItem.setSearchable(oneTextSearch);
                }
                break;
            case TYPE_BODY:
                if (typeBodyComboBox.getValue() != null) {
                    myFilterItem = new OneTextValue(enumColumnNamesForPerson);
                    Searchable oneTextSearch = new OneTextSearch(typeBodyComboBox.getValue().name());
                    myFilterItem.setSearchable(oneTextSearch);
                }
                break;
            case YEAR_OF_BUILD:
                if (startDate.getValue() != null && finishDate != null) {
                    Date from = Date.from(
                            startDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());

                    Date to = Date.from(finishDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
                    myFilterItem = new TwoDateValue(enumColumnNamesForPerson);
                    Datable twoDate = new TwoDate(from, to);
                    myFilterItem.setDatable(twoDate);
                }
                break;
            case ECCO_OF_ENGINE:
                if (numberComboBox.getValue() != null) {
                    myFilterItem = new OneTextValue(enumColumnNamesForPerson);
                    Searchable oneTextSearch = new OneTextSearch(numberComboBox.getValue().toString());
                    myFilterItem.setSearchable(oneTextSearch);
                }

                break;
            case NUMBER_OF_ENGINE:
                if (searchField.getValue() != null) {
                    myFilterItem = new OneTextValue(enumColumnNamesForPerson);
                    Searchable oneTextSearch = new OneTextSearch(searchField.getValue().trim());
                    myFilterItem.setSearchable(oneTextSearch);
                }
                break;
            case NUMBER_OF_CHASSIS:
                if (searchField.getValue() != null) {
                    myFilterItem = new OneTextValue(enumColumnNamesForPerson);
                    Searchable oneTextSearch = new OneTextSearch(searchField.getValue().trim());
                    myFilterItem.setSearchable(oneTextSearch);
                }
                break;
            case NUMBER_OF_BODY:
                if (searchField.getValue() != null) {
                    myFilterItem = new OneTextValue(enumColumnNamesForPerson);
                    Searchable oneTextSearch = new OneTextSearch(searchField.getValue().trim());
                    myFilterItem.setSearchable(oneTextSearch);
                }
                break;
            case POWER_OF_ENGINE:
                if (from.getValue() != null && to.getValue() != null) {
                    myFilterItem = new TwoDateValue(enumColumnNamesForPerson);
                    Searchable searchable = new TwoTextSearch(from.getValue().trim(), to.getValue().trim());
                    myFilterItem.setSearchable(searchable);
                }
                break;
            case VOLUME_OF_ENGINE:
                if (from.getValue() != null && to.getValue() != null) {
                    myFilterItem = new TwoDateValue(enumColumnNamesForPerson);
                    Searchable searchable = new TwoTextSearch(from.getValue().trim(), to.getValue().trim());
                    myFilterItem.setSearchable(searchable);
                }
                break;
            case MAX_MASS:
                if (from.getValue() != null && to.getValue() != null) {
                    myFilterItem = new TwoDateValue(enumColumnNamesForPerson);
                    Searchable searchable = new TwoTextSearch(from.getValue().trim(), to.getValue().trim());
                    myFilterItem.setSearchable(searchable);
                }
                break;
            case MAX_MASS_WITHOUT:
                if (from.getValue() != null && to.getValue() != null) {
                    myFilterItem = new TwoDateValue(enumColumnNamesForPerson);
                    Searchable searchable = new TwoTextSearch(from.getValue().trim(), to.getValue().trim());
                    myFilterItem.setSearchable(searchable);
                }
                break;
            case NUMBER_OF_PASSPORT_TS:
                if (searchField.getValue() != null) {
                    myFilterItem = new OneTextValue(enumColumnNamesForPerson);
                    Searchable oneTextSearch = new OneTextSearch(searchField.getValue().trim());
                    myFilterItem.setSearchable(oneTextSearch);
                }
                break;
            case REG_NUMBER:
                if (searchField.getValue() != null) {
                    myFilterItem = new OneTextValue(enumColumnNamesForPerson);
                    Searchable oneTextSearch = new OneTextSearch(searchField.getValue().trim());
                    myFilterItem.setSearchable(oneTextSearch);
                }
                break;
            case QUANTITY_OF_PALLET:
                if (from.getValue() != null && to.getValue() != null) {
                    myFilterItem = new TwoDateValue(enumColumnNamesForPerson);
                    Searchable searchable = new TwoTextSearch(from.getValue().trim(), to.getValue().trim());
                    myFilterItem.setSearchable(searchable);
                }
                break;
            case WIDHT_OF_BODY:
                if (from.getValue() != null && to.getValue() != null) {
                    myFilterItem = new TwoDateValue(enumColumnNamesForPerson);
                    Searchable searchable = new TwoTextSearch(from.getValue().trim(), to.getValue().trim());
                    myFilterItem.setSearchable(searchable);
                }
                break;
            case HEIGHT_OF_BODY:
                if (from.getValue() != null && to.getValue() != null) {
                    myFilterItem = new TwoDateValue(enumColumnNamesForPerson);
                    Searchable searchable = new TwoTextSearch(from.getValue().trim(), to.getValue().trim());
                    myFilterItem.setSearchable(searchable);
                }
                break;
            case LENGHT_OF_BODY:
                if (from.getValue() != null && to.getValue() != null) {
                    myFilterItem = new TwoDateValue(enumColumnNamesForPerson);
                    Searchable searchable = new TwoTextSearch(from.getValue().trim(), to.getValue().trim());
                    myFilterItem.setSearchable(searchable);
                }
                break;
            case VOLUME_OF_BODY:
                if (from.getValue() != null && to.getValue() != null) {
                    myFilterItem = new TwoDateValue(enumColumnNamesForPerson);
                    Searchable searchable = new TwoTextSearch(from.getValue().trim(), to.getValue().trim());
                    myFilterItem.setSearchable(searchable);
                }
                break;
            default:
                System.out.println("Дефолтное значение");
        }
        return myFilterItem;

    }


    private void changeSearchFields(AbstractField.ComponentValueChangeEvent<ComboBox<EnumColumnNamesForPerson>, EnumColumnNamesForPerson> event) {
        String label = event.getValue().getDisplayName() + ":";
        searchField.setLabel(label);
        yesNOComboBox.setLabel(label);
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
            default:
                System.out.println("Дефолтное значение");
        }
        searchFlexLayout.setAlignItems(FlexComponent.Alignment.BASELINE);

    }


    private void updateListItems() {
        DataProvider<, MyFilterItem> dataProvider = DataProvider.fromFilteringCallbacks(
                // First callback fetches items based on a query
                query -> {

                    List<Car> cars = personService.findByExample(query.getFilter(), query.getOffset(), query.getLimit());
                    return cars.stream();
                },
                // Second callback fetches the number of items for a query
                query -> personService.getCount(query.getFilter()));

        carVoidVoidConfigurableFilterDataProvider = dataProvider.withConfigurableFilter();
        grid.setDataProvider(carVoidVoidConfigurableFilterDataProvider);
    }

    private String[] getQueryPropperty(Optional<String> query) {
        String[] arr = null;

        if (query.isPresent() && columnNamesComboBox.getValue() != null) {
            arr = new String[]{columnNamesComboBox.getValue().getColumnSearchName(), query.get()};
        }

        return arr;
    }


    private void refreshyourObjectGrid() {
        enumColumnNameSearchSelectedForCar = columnNamesComboBox.getValue();
        MyFilterItem myFilterItem = null;

        if (enumColumnNameSearchSelectedForCar != null) {
            myFilterItem = getItemFoeSearch(enumColumnNameSearchSelectedForCar);
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
        grid.addColumn(person -> person.getBirthhday()).setHeader("Дата рождения").setResizable(true);
        grid.addColumn(person -> person.getEnumTypePerson().name()).setHeader("Тип").setResizable(true);
//
        grid.getSelectionModel().addSelectionListener(new SelectionListener<Grid<Person>, Person>() {
            @Override
            public void selectionChange(SelectionEvent<Grid<Person>, Person> event) {
                boolean somethingSelected = !grid.getSelectedItems().isEmpty();
                if (somethingSelected) {
                    selectedPerson = event.getFirstSelectedItem().get();
                    selection.selectItem(selectedPerson);
                    //openEditor(event.getFirstSelectedItem().get());
                }
            }
        });


        add(grid);
    }

//    private void openEditor(Person person) {
//        Dialog editorDialog = new Dialog();
//        Button save = new Button("Cохранить");
//        Button cancel = new Button("Отмена");
//        Button delete = new Button("Удалить");
//        editorDialog.add(personEditor);
//        personEditor.getElement().getStyle().set("overflow", "auto");
//        FlexLayout submitLayout = new FlexLayout();
//        submitLayout.add(save, cancel, delete);
//        save.getElement().getThemeList().add("primary");
//        delete.getElement().getThemeList().add("error");
//        submitLayout.setAlignItems(FlexComponent.Alignment.END);
//        editorDialog.add(submitLayout);
//
//        personEditor.setChangeHandler(() -> {
//            editorDialog.close();
//            refreshyourObjectGrid();
//            //updateListItems();
//        });
//        cancel.addClickListener(new ComponentEventListener<ClickEvent<Button>>() {
//            @Override
//            public void onComponentEvent(ClickEvent<Button> event) {
//                //    editorDialog.removeAll();
//                editorDialog.close();
//            }
//        });
//
//        save.addClickListener(event -> {
//            personEditor.save();
//        });
//
//        delete.addClickListener(event -> personEditor.deleteCar());
//
//        personEditor.editCar(person);
//        personEditor.setSaveButton(save);
//        editorDialog.setHeight("600px");
//        editorDialog.setWidth("1200px");
//        editorDialog.open();
//    }


}
