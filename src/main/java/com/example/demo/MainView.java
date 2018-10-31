package com.example.demo;

import com.example.demo.entity.cars.car.*;
import com.example.demo.entity.cars.utils.search.*;
import com.example.demo.services.CarService;
import com.example.demo.services.LoginService;
import com.vaadin.flow.component.*;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Setter;
import com.vaadin.flow.data.provider.ConfigurableFilterDataProvider;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.selection.SelectionEvent;
import com.vaadin.flow.data.selection.SelectionListener;
import com.vaadin.flow.function.SerializablePredicate;
import com.vaadin.flow.function.ValueProvider;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;


import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@HtmlImport("styles/styles.html")
//@Route(value = "main")
@Route(value = "login")
public class MainView extends VerticalLayout {

    @Autowired
    LoginService loginService;

    private static final String NAME_OF_MENU_GENERAL = "Основные";
    private static final String MENU_ITEM_LOGOUT = "Выход";
    private static final String ADD_BTN_TEXT = "Добавить";
    private static final String SEARCH_TEXT_PLACEHOLDER = "поиск";

    private CarService carService;
    private Button addBtn;
    private Grid<Car> grid;
    private CarEditor carEditor;
    private ConfigurableFilterDataProvider<Car, Void, MyFilterItem> carVoidVoidConfigurableFilterDataProvider;
    private HorizontalLayout searchFlexLayout;
    private ComboBox<EnumColumnNames> columnNamesComboBox;
    private TextField searchField = new TextField("Строка поиска", "поиск");
    private TextField from = new TextField("От:");
    private TextField to = new TextField("До:");
    private DatePicker startDate = new DatePicker("С даты:");
    private DatePicker finishDate = new DatePicker("По дату:");
    private ComboBox<EnumYesNo> yesNOComboBox = new ComboBox("Да/Нет:");
    private ComboBox<EnumTypeFuel> typeFuelComboBox = new ComboBox("Тип топлива:");
    private ComboBox<Integer> numberComboBox = new ComboBox<>();
    private Button searchBtn;
    private Div additionalGreedMenuLayout; // лайяут для доп выбора при поиске
    private ComboBox<EnumTypeOfBody> typeTsComboBox = new ComboBox<>("Тип ТС:");
    private EnumColumnNames enumColumnNameSearchSelected = null;

    public MainView(CarService carService, CarEditor carEditor) {
        this.carService = carService;
        this.carEditor = carEditor;
        createMenu();
        createSearchMenu();
        createGreedWithCars();
        updateListItems();
    }

    private void createSearchMenu() {
        VerticalLayout greedMenuLayout = new VerticalLayout();
        FlexLayout searchLayout = new FlexLayout();
        addBtn = new Button(ADD_BTN_TEXT, VaadinIcon.PLUS.create());
        addBtn.addClickListener(event -> {
            openEditor(new Car());
        });
        greedMenuLayout.add(addBtn, searchLayout);

        searchFlexLayout = new HorizontalLayout();
        columnNamesComboBox = new ComboBox<>();
        columnNamesComboBox.setLabel("Выбор критерия:");
        columnNamesComboBox.setWidth("100%");
        columnNamesComboBox.setPlaceholder("Поиск по:");
        columnNamesComboBox.setFilteredItems();
        columnNamesComboBox.setItems(Arrays.stream(EnumColumnNames.values()).filter((s) ->
                s.getVisibleForCombobox()
        ));
        searchBtn = new Button(VaadinIcon.SEARCH.create());
        searchBtn.addClickListener((s) -> {
            refreshyourObjectGrid();
        });
        columnNamesComboBox.addValueChangeListener(new HasValue.ValueChangeListener<AbstractField.ComponentValueChangeEvent<ComboBox<EnumColumnNames>, EnumColumnNames>>() {
            @Override
            public void valueChanged(AbstractField.ComponentValueChangeEvent<ComboBox<EnumColumnNames>, EnumColumnNames> event) {
                additionalGreedMenuLayout.removeAll();
                if (event.getValue() != null) {
                    System.out.println(event.getValue().getDisplayName());
                    changeSearchFields(event);
                }
            }
        });

        additionalGreedMenuLayout = new Div();
        searchFlexLayout.add(columnNamesComboBox, additionalGreedMenuLayout, searchBtn);
        searchFlexLayout.setAlignItems(Alignment.BASELINE);
        greedMenuLayout.add(searchFlexLayout);
        add(greedMenuLayout);
    }



    private MyFilterItem getItemFoeSearch(EnumColumnNames enumColumnNames) {
        MyFilterItem myFilterItem = null;
        switch (enumColumnNames) {
            case DATE_OF_TAKE_TO_BALLANCE:
                if(startDate.getValue() != null && finishDate != null){
                    Date from = Date.from(
                            startDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
                    Date to = Date.from(finishDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
                    myFilterItem = new TwoDateValue(enumColumnNames);
                    TwoDate twoDate = new TwoDate(from, to);
                    myFilterItem.setDatable(twoDate);
                }
                break;
            case DECOMISSIONED:
                EnumYesNo value = yesNOComboBox.getValue();
                if(value != null){
                    Check check = new Check(value.isYes());
                    myFilterItem = new CheckValue(enumColumnNames);
                    myFilterItem.setCheckable(check);
                }

                break;
            case DATE_OF_COMMISSIONED:
                if(startDate.getValue() != null && finishDate != null){
                    Date from = Date.from(
                            startDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
                    Date to = Date.from(finishDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
                    myFilterItem = new TwoDateValue(enumColumnNames);
                    TwoDate twoDate = new TwoDate(from, to);
                    myFilterItem.setDatable(twoDate);
                }

                break;
            case FAULY:
                System.out.println("f" +yesNOComboBox.getValue().isYes());
                if(yesNOComboBox.getValue() != null){
                    myFilterItem = new CheckValue(enumColumnNames);
                    Check check = new Check(yesNOComboBox.getValue().isYes());
                    myFilterItem.setCheckable(check);
                }
                break;
            case PODRAZDELENIE_OR_GARAGE:
                break;
            case COLONNA:
                break;
            case NUMBER_OF_GARAGE:
                break;
            case NUMBER_OF_INVENTAR:
                break;
            case TYPE_OF_FUEL:
                break;
            case MILEAGE:
                break;
            case MASHINE_HOURS:
                break;
            case VIN:

                break;
            case TYPE_TS:

                break;
            case YEAR_OF_BUILD:

                break;
            case ECCO_OF_ENGINE:

                break;
            case NUMBER_OF_ENGINE:

                break;
            case NUMBER_OF_CHASSIS:

                break;
            case NUMBER_OF_BODY:

                break;
            case POWER_OF_ENGINE:

                break;
            case VOLUME_OF_ENGINE:
                break;
            case MAX_MASS:
                break;
            case MAX_MASS_WITHOUT:
                break;
            case NUMBER_OF_PASSPORT_TS:
                break;
            case REG_NUMBER:
                break;
            case QUANTITY_OF_PALLET:
                break;
            case WIDHT_OF_BODY:
                break;
            case HEIGHT_OF_BODY:
                break;
            case LENGHT_OF_BODY:
                break;
            case VOLUME_OF_BODY:
                break;
            default:
                System.out.println("Дефолтное значение");
        }
        return myFilterItem;

    }


    private void changeSearchFields(AbstractField.ComponentValueChangeEvent<ComboBox<EnumColumnNames>, EnumColumnNames> event) {
        String label = event.getValue().getDisplayName() + ":";
        searchField.setLabel(label);
        yesNOComboBox.setLabel(label);
        switch (event.getValue()) {
            case DATE_OF_TAKE_TO_BALLANCE:
                additionalGreedMenuLayout.add(startDate, finishDate);
                break;
            case DECOMISSIONED:
                yesNOComboBox.setItems(EnumYesNo.values());
                additionalGreedMenuLayout.add(yesNOComboBox);
                break;
            case DATE_OF_COMMISSIONED:
                additionalGreedMenuLayout.add(startDate, finishDate);
                break;
            case FAULY:
                yesNOComboBox.setItems(EnumYesNo.values());
                additionalGreedMenuLayout.add(yesNOComboBox);
                break;
            case PODRAZDELENIE_OR_GARAGE:
                additionalGreedMenuLayout.add(searchField);
                break;
            case COLONNA:
                additionalGreedMenuLayout.add(searchField);
                break;
            case NUMBER_OF_GARAGE:
                additionalGreedMenuLayout.add(searchField);
                break;
            case NUMBER_OF_INVENTAR:
                additionalGreedMenuLayout.add(searchField);
                break;
            case TYPE_OF_FUEL:
                typeFuelComboBox.setItems(EnumTypeFuel.values());
                additionalGreedMenuLayout.add(typeFuelComboBox);
                break;
            case MILEAGE:
                additionalGreedMenuLayout.add(from, to);
                break;
            case MASHINE_HOURS:
                additionalGreedMenuLayout.add(from, to);
                break;
            case VIN:
                additionalGreedMenuLayout.add(searchField);
                break;
            case TYPE_TS:
                additionalGreedMenuLayout.add(typeTsComboBox);
                break;
            case YEAR_OF_BUILD:
                additionalGreedMenuLayout.add(startDate);
                break;
            case ECCO_OF_ENGINE:
                numberComboBox.setLabel(label);
                numberComboBox.setItems(1, 2, 3, 4, 5);
                additionalGreedMenuLayout.add(numberComboBox);
                break;
            case NUMBER_OF_ENGINE:
                additionalGreedMenuLayout.add(searchField);
                break;
            case NUMBER_OF_CHASSIS:
                additionalGreedMenuLayout.add(searchField);
                break;
            case NUMBER_OF_BODY:
                additionalGreedMenuLayout.add(searchField);
                break;
            case POWER_OF_ENGINE:
                additionalGreedMenuLayout.add(searchField);
                break;
            case VOLUME_OF_ENGINE:
                additionalGreedMenuLayout.add(searchField);
                break;
            case MAX_MASS:
                additionalGreedMenuLayout.add(searchField);
                break;
            case MAX_MASS_WITHOUT:
                additionalGreedMenuLayout.add(searchField);
                break;
            case NUMBER_OF_PASSPORT_TS:
                additionalGreedMenuLayout.add(searchField);
                break;
            case REG_NUMBER:
                additionalGreedMenuLayout.add(searchField);
                break;
            case QUANTITY_OF_PALLET:
                additionalGreedMenuLayout.add(searchField);
                break;
            case WIDHT_OF_BODY:
                additionalGreedMenuLayout.add(searchField);
                break;
            case HEIGHT_OF_BODY:
                additionalGreedMenuLayout.add(searchField);
                break;
            case LENGHT_OF_BODY:
                additionalGreedMenuLayout.add(searchField);
                break;
            case VOLUME_OF_BODY:
                additionalGreedMenuLayout.add(searchField);
                break;
            default:
                System.out.println("Дефолтное значение");
        }
        searchFlexLayout.setAlignItems(Alignment.BASELINE);

    }


    private void updateListItems() {

        DataProvider<Car, MyFilterItem> dataProvider = DataProvider.fromFilteringCallbacks(
                // First callback fetches items based on a query
                query -> {

                    List<Car> cars = carService.findByExample(query.getFilter(), query.getOffset(), query.getLimit());
                    return cars.stream();
                },
                // Second callback fetches the number of items for a query
                query -> carService.getCount(query.getFilter()));

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
        System.out.println("list");
        enumColumnNameSearchSelected = columnNamesComboBox.getValue();
        MyFilterItem myFilterItem = null;

        if (enumColumnNameSearchSelected != null) {
            myFilterItem = getItemFoeSearch(enumColumnNameSearchSelected);
        }

        carVoidVoidConfigurableFilterDataProvider.setFilter(myFilterItem);
        grid.getDataProvider().refreshAll();
    }

    private SerializablePredicate<Car> filterYourObjectGrid(Optional<String> i) {
        SerializablePredicate<Car> columnPredicate;
        if (!i.isPresent()) {
            columnPredicate = y -> true;
        } else columnPredicate = yourObject -> (yourObject.getId() == Integer.parseInt(i.get()));

        return columnPredicate;
    }


    private void createMenu() {
        FlexLayout loginFlexLayout = new FlexLayout();
        loginFlexLayout.setWidth("100%");
        loginFlexLayout.setAlignItems(Alignment.BASELINE);

        Label loginNameLabel = new Label(" МарьИванна");

        Button buttonExit = new Button(VaadinIcon.EXIT.create());

        loginFlexLayout.add(loginNameLabel, buttonExit);
        setHorizontalComponentAlignment(Alignment.END, loginFlexLayout);

        add(loginFlexLayout);
    }

    private void createGreedWithCars() {

        grid = new Grid<>();

        grid.setSelectionMode(Grid.SelectionMode.SINGLE);
        //    grid.setItems(cars);;
        // grid.addColumn(car -> car.getId(), "id").setResizable(true).setHeader(getSearchFieldAndHeader("id"));
        grid.addColumn(car -> car.getId()).setHeader("ID").setResizable(true);
//        grid.addColumn(car -> car.getPassportData().getRegNumber()).setHeader("Рег.знак").setResizable(true);
//        grid.addColumn(car -> car.getPassportData().getVin()).setHeader("VIN").setResizable(true);
//        grid.addColumn(car -> car.getPassportData().getVin()).setHeader("ГТО до").setResizable(true);
//        grid.addColumn(car -> car.getPassportData().getTypeTS()).setHeader("Тип ТС").setResizable(true);
//        grid.addColumn(car -> car.getPassportData().getModelTS()).setHeader("Модель ТС").setResizable(true);
//        //grid.addColumn(car -> car.getPassportData().getVin()).setHeader("Стрх. до");
//        grid.addColumn(car -> car.getPassportData().getYearOfBuild()).setHeader("Год выпуска").setResizable(true);
//        grid.addColumn(car -> car.getPassportData().getCategory()).setHeader("Категория").setResizable(true);
        //  grid.addColumn(car -> car.getGeneralData().getPodrazdelenieOrGarage()).setHeader(getSearchFieldAndHeader("Подразделение(гараж)")).setResizable(true);
        grid.addColumn(car -> car.getGeneralData().getNumberOfGarage()).setHeader("Номер Гаража").setResizable(true);
        //   grid.addColumn(car -> car.getPassportData().getVin()).setHeader("Каско до");
        grid.addColumn(car -> car.getGeneralData().getComment()).setHeader("Комментарий").setResizable(true);
        grid.addColumn(car -> car.getGeneralData().getMileage()).setHeader("Пробег").setResizable(true);
        //    grid.addColumn(car -> car.getGeneralData().getDateOfMileage()).setHeader("Дата пробега");

//        grid.addColumn(car -> car.getGeneralData().getMileage()).setHeader("VIN4");
//        grid.addColumn(car -> car.getGeneralData().getMileage()).setHeader("Ближайшие ТО");
//        grid.addColumn(car -> car.getGeneralData().getMileage()).setHeader("Бригада");
//        grid.addColumn(car -> car.getGeneralData().getMileage()).setHeader("Вид топлива");
//        grid.addColumn(car -> car.getGeneralData().getMileage()).setHeader("Водитель");
//        grid.addColumn(car -> car.getGeneralData().getMileage()).setHeader("Временная регистрация до");
//        grid.addColumn(car -> car.getGeneralData().getMileage()).setHeader("Высота");
//        grid.addColumn(car -> car.getGeneralData().getMileage()).setHeader("Группа");
//        grid.addColumn(car -> car.getGeneralData().getMileage()).setHeader("ГТО №");
        grid.addColumn(car -> car.getGeneralData().getDateOfTakeToBalanse()).setHeader("Дата принятия на баланс").setResizable(true);
//        grid.addColumn(car -> car.getGeneralData().getMileage()).setHeader("Дата списания");
//        grid.addColumn(car -> car.getGeneralData().getMileage()).setHeader("Двигатель №");
//        grid.addColumn(car -> car.getGeneralData().getMileage()).setHeader("Двиг. модель");
//        grid.addColumn(car -> car.getGeneralData().getMileage()).setHeader("Длина");
//        grid.addColumn(car -> car.getGeneralData().getMileage()).setHeader("Допполе 1");
//        grid.addColumn(car -> car.getGeneralData().getMileage()).setHeader("Допполе 2");
//        grid.addColumn(car -> car.getGeneralData().getMileage()).setHeader("Допполе 3");
//        grid.addColumn(car -> car.getGeneralData().getMileage()).setHeader("Зел карта до");
//        grid.addColumn(car -> car.getGeneralData().getMileage()).setHeader("Зел карта компания");
//        grid.addColumn(car -> car.getGeneralData().getMileage()).setHeader("Зел карта номер");
//        grid.addColumn(car -> car.getGeneralData().getMileage()).setHeader("Зел карта от");
//        grid.addColumn(car -> car.getGeneralData().getMileage()).setHeader("Зел карта премия");
//        grid.addColumn(car -> car.getGeneralData().getMileage()).setHeader("Зел карта цель");
//        grid.addColumn(car -> car.getGeneralData().getMileage()).setHeader("Идентификатор");
//        grid.addColumn(car -> car.getGeneralData().getMileage()).setHeader("Изменено");
//        grid.addColumn(car -> car.getGeneralData().getMileage()).setHeader("Калибровка тах до");
//        grid.addColumn(car -> car.getGeneralData().getMileage()).setHeader("КАСКО компания");
//        grid.addColumn(car -> car.getGeneralData().getMileage()).setHeader("КАСКО номер");
//        grid.addColumn(car -> car.getGeneralData().getMileage()).setHeader("КАСКО от");
//        grid.addColumn(car -> car.getGeneralData().getMileage()).setHeader("КАСКО премия");
//        grid.addColumn(car -> car.getGeneralData().getMileage()).setHeader("КАСКО цель");
//        grid.addColumn(car -> car.getGeneralData().getMileage()).setHeader("Кем выдан паспорт");
//        grid.addColumn(car -> car.getGeneralData().getMileage()).setHeader("Кол европалет");
//        grid.addColumn(car -> car.getGeneralData().getMileage()).setHeader("Коллона");
//        grid.addColumn(car -> car.getGeneralData().getMileage()).setHeader("Контр дата 1");
//        grid.addColumn(car -> car.getGeneralData().getMileage()).setHeader("Контр дата 2");
//        grid.addColumn(car -> car.getGeneralData().getMileage()).setHeader("Кузов №");
//        grid.addColumn(car -> car.getGeneralData().getMileage()).setHeader("Литров на 100км");
//        grid.addColumn(car -> car.getGeneralData().getMileage()).setHeader("Лицензия");
//        grid.addColumn(car -> car.getGeneralData().getMileage()).setHeader("Макс масса");
//        grid.addColumn(car -> car.getGeneralData().getMileage()).setHeader("Масса без нагрузки");
//        grid.addColumn(car -> car.getGeneralData().getMileage()).setHeader("Место рег");
//        grid.addColumn(car -> car.getGeneralData().getMileage()).setHeader("Модель тахографа");
//        grid.addColumn(car -> car.getGeneralData().getMileage()).setHeader("Мотор масла");
//        grid.addColumn(car -> car.getGeneralData().getMileage()).setHeader("Моточасы кран/доп.об");
//        grid.addColumn(car -> car.getGeneralData().getMileage()).setHeader("Мощность");
//        grid.addColumn(car -> car.getGeneralData().getMileage()).setHeader("МТО до");
//        grid.addColumn(car -> car.getGeneralData().getMileage()).setHeader("МТО номер");
//        grid.addColumn(car -> car.getGeneralData().getMileage()).setHeader("Номер тахографа");
//        grid.addColumn(car -> car.getGeneralData().getMileage()).setHeader("Норма расхода");
//        grid.addColumn(car -> car.getGeneralData().getMileage()).setHeader("Обьем двигателя");
//        grid.addColumn(car -> car.getGeneralData().getMileage()).setHeader("Обьем фургона");
//        grid.addColumn(car -> car.getGeneralData().getMileage()).setHeader("ОПО до");
//        grid.addColumn(car -> car.getGeneralData().getMileage()).setHeader("ОПО компания");
//        grid.addColumn(car -> car.getGeneralData().getMileage()).setHeader("ОПО номер");
//        grid.addColumn(car -> car.getGeneralData().getMileage()).setHeader("ОПО от");
//        grid.addColumn(car -> car.getGeneralData().getMileage()).setHeader("ОПО премия");
//        grid.addColumn(car -> car.getGeneralData().getMileage()).setHeader("ОПО цель");
//        grid.addColumn(car -> car.getGeneralData().getMileage()).setHeader("Пасп. дата");
//        grid.addColumn(car -> car.getGeneralData().getMileage()).setHeader("Пасп. №");
//        grid.addColumn(car -> car.getGeneralData().getMileage()).setHeader("Пласт смазка");
//        grid.addColumn(car -> car.getGeneralData().getMileage()).setHeader("Платон");
//        grid.addColumn(car -> car.getGeneralData().getMileage()).setHeader("Поверка тахографа до");
//        grid.addColumn(car -> car.getGeneralData().getMileage()).setHeader("Пользователь");
//        grid.addColumn(car -> car.getGeneralData().getMileage()).setHeader("Пометка для военкомата");
//        grid.addColumn(car -> car.getGeneralData().getMileage()).setHeader("Прицеп");
//        grid.addColumn(car -> car.getGeneralData().getMileage()).setHeader("Ппроизводитель");
//        grid.addColumn(car -> car.getGeneralData().getMileage()).setHeader("ПТО до");
//        grid.addColumn(car -> car.getGeneralData().getMileage()).setHeader("ПТО номер");
//        grid.addColumn(car -> car.getGeneralData().getMileage()).setHeader("Расход по моточасам");
//        grid.addColumn(car -> car.getGeneralData().getMileage()).setHeader("Расход при вып.работы");
//        grid.addColumn(car -> car.getGeneralData().getMileage()).setHeader("Рег. знак старый");
//        grid.addColumn(car -> car.getGeneralData().getMileage()).setHeader("Режим работы");
//        grid.addColumn(car -> car.getGeneralData().getMileage()).setHeader("Св-во номер");
//        grid.addColumn(car -> car.getGeneralData().getMileage()).setHeader("Св-во дата");
//        grid.addColumn(car -> car.getGeneralData().getMileage()).setHeader("Собственник");
//        grid.addColumn(car -> car.getGeneralData().getMileage()).setHeader("Спец. Жидкий");
//        grid.addColumn(car -> car.getGeneralData().getMileage()).setHeader("Срок договора");
//        grid.addColumn(car -> car.getGeneralData().getMileage()).setHeader("Срок лицензии");
//        grid.addColumn(car -> car.getGeneralData().getMileage()).setHeader("Стоимость");
//        grid.addColumn(car -> car.getGeneralData().getMileage()).setHeader("Стрх.компания");
//        grid.addColumn(car -> car.getGeneralData().getMileage()).setHeader("Стрх.номер");
//        grid.addColumn(car -> car.getGeneralData().getMileage()).setHeader("Стрх. от");
//        grid.addColumn(car -> car.getGeneralData().getMileage()).setHeader("Стрх. премия");
//        grid.addColumn(car -> car.getGeneralData().getMileage()).setHeader("Стрх. цель");
//        grid.addColumn(car -> car.getGeneralData().getMileage()).setHeader("Тех. состояние");
//        grid.addColumn(car -> car.getGeneralData().getMileage()).setHeader("Тип кузова/фургона");
//        grid.addColumn(car -> car.getGeneralData().getMileage()).setHeader("Тр.Гидр.Масла");
//        grid.addColumn(car -> car.getGeneralData().getMileage()).setHeader("Филиал");
//        grid.addColumn(car -> car.getGeneralData().getMileage()).setHeader("Цвет");
//        grid.addColumn(car -> car.getGeneralData().getMileage()).setHeader("Цена по дог.");
//        grid.addColumn(car -> car.getGeneralData().getMileage()).setHeader("ЧТО до");
//        grid.addColumn(car -> car.getGeneralData().getMileage()).setHeader("ЧТО номер");
//        grid.addColumn(car -> car.getGeneralData().getMileage()).setHeader("Шасси №");
//        grid.addColumn(car -> car.getGeneralData().getMileage()).setHeader("Ширина");
//        grid.addColumn(car -> car.getGeneralData().getMileage()).setHeader("ЭКО клас");
//


//        grid.asSingleSelect().addValueChangeListener(e -> {
//            openEditor(e.getValue());
//
//        });
        grid.getSelectionModel().addSelectionListener(new SelectionListener<Grid<Car>, Car>() {
            @Override
            public void selectionChange(SelectionEvent<Grid<Car>, Car> event) {
                boolean somethingSelected = !grid.getSelectedItems().isEmpty();
                if (somethingSelected) {
                    openEditor(event.getFirstSelectedItem().get());
                }
            }
        });


        add(grid);
    }

    private void openEditor(Car car) {
        Dialog dialog = new Dialog();
        Button save = new Button("Cохранить");
        Button cancel = new Button("Отмена");
        Button delete = new Button("Удалить");
        dialog.add(carEditor);
        carEditor.getElement().getStyle().set("overflow", "auto");
        FlexLayout submitLayout = new FlexLayout();
        submitLayout.add(save, cancel, delete);
        save.getElement().getThemeList().add("primary");
        delete.getElement().getThemeList().add("error");
        submitLayout.setAlignItems(Alignment.END);
        dialog.add(submitLayout);

        carEditor.setChangeHandler(() -> {
            dialog.close();
            refreshyourObjectGrid();
            //updateListItems();
        });
        cancel.addClickListener(new ComponentEventListener<ClickEvent<Button>>() {
            @Override
            public void onComponentEvent(ClickEvent<Button> event) {
                //    dialog.removeAll();
                dialog.close();
            }
        });

        save.addClickListener(event -> {
            carEditor.save();
        });

        delete.addClickListener(event -> carEditor.deleteCar());

        carEditor.editCar(car);
        carEditor.setSaveButton(save);
        dialog.setHeight("600px");
        dialog.setWidth("1200px");
        dialog.open();
    }


}
