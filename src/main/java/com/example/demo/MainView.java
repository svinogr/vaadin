package com.example.demo;

import com.example.demo.entity.cars.car.Car;
import com.example.demo.services.CarService;
import com.example.demo.services.LoginService;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.HeaderRow;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.ConfigurableFilterDataProvider;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.selection.SelectionEvent;
import com.vaadin.flow.data.selection.SelectionListener;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.function.SerializablePredicate;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.gridutil.cell.GridCellFilter;

import java.awt.*;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

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
    private TextField searchField;
    private Grid<Car> grid;
    private CarEditor carEditor;
    ConfigurableFilterDataProvider<Car, Void, String> carVoidVoidConfigurableFilterDataProvider;
    private ConfigurableFilterDataProvider<Car, Void, SerializablePredicate<Car>> filterYourObjectDataProvider;
    private TextField filterField;

    public MainView(CarService carService, CarEditor carEditor) {
        this.carService = carService;
        this.carEditor = carEditor;
        createMenu();
        createSearchMenu();
        createGreedWithCars();
        updateListItems();
    }

    private void createSearchMenu() {
        HorizontalLayout greedMenuLayout = new HorizontalLayout();
        FlexLayout searchLayout = new FlexLayout();
        addBtn = new Button(ADD_BTN_TEXT, VaadinIcon.PLUS.create());
        addBtn.addClickListener(event -> {
            openEditor(new Car());
        });
//
//        searchField = new TextField();
//        searchField.setPlaceholder(SEARCH_TEXT_PLACEHOLDER);
//        searchField.addValueChangeListener(e -> updateListItems());
        //  searchField.setValueChangeMode(ValueChangeMode.EAGER);
//
//        Button clearBtn = new Button(VaadinIcon.CLOSE.create());
//        clearBtn.addClickListener(event -> {
//            searchField.clear();
//            updateListItems();
//        });

        // searchLayout.add(searchField, clearBtn);
        greedMenuLayout.add(addBtn, searchLayout);
        add(greedMenuLayout);
    }

    private void updateListItems() {
//        Car carSearch = null;
//        if (!searchField.isEmpty()) {
//            long id = Long.parseLong(searchField.getValue());
//            carSearch = new Car();
//            carSearch.setId(id);
//        }

        //Car finalCarSearch = carSearch;
        DataProvider<Car, String> dataProvider = DataProvider.fromFilteringCallbacks(
                // First callback fetches items based on a query
                query -> {
                    System.out.println(query.getFilter());
                    List<Car> cars = carService.findByExample(query.getFilter(), query.getOffset(), query.getLimit());
                    System.out.println("hjhghjfhjffghfgfhfhfhf" + cars.size());
                    // return cars.stream();
                    return cars.stream();
                },
                // Second callback fetches the number of items for a query
                query -> carService.getCount(query.getFilter()));

        carVoidVoidConfigurableFilterDataProvider = dataProvider.withConfigurableFilter();

        //   carVoidVoidConfigurableFilterDataProvider = dataProvider.withConfigurableFilter();


        grid.setDataProvider(carVoidVoidConfigurableFilterDataProvider);
        // grid.setDataProvider(this.carVoidVoidConfigurableFilterDataProvider);
    }

    private void refreshyourObjectGrid() {
        String value = filterField.getValue().trim();
        String filter;
        System.out.println(value+"dedededdddddddddddddddddddd");
        if(value.isEmpty()){
            filter = null;
        }else filter = value;


        carVoidVoidConfigurableFilterDataProvider.setFilter(filter);
        // updateListItems();
        grid.getDataProvider().refreshAll();
    }

    private SerializablePredicate<Car> filterYourObjectGrid(Optional<String> i) {
        SerializablePredicate<Car> columnPredicate;
        System.out.println(i);

        if (!i.isPresent()) {
            columnPredicate = y -> true;
        } else columnPredicate = yourObject -> (yourObject.getId() == Integer.parseInt(i.get()));

        return columnPredicate;
    }


    private void createMenu() {
        HorizontalLayout menulayout = new HorizontalLayout();

        ComboBox<String> mainListBoxMenu = new ComboBox<>();
        mainListBoxMenu.setLabel(NAME_OF_MENU_GENERAL);
        mainListBoxMenu.addValueChangeListener(event -> {
            String eventName = event.getValue();
            switch (eventName) {
                case MENU_ITEM_LOGOUT:
                    loginService.logout();
                    UI.getCurrent().getPage().reload();
                    //   getUI().ifPresent(ui -> ui.navigate("login"));
                    break;
                case "":

                    break;
            }

        });
        mainListBoxMenu.setItems(MENU_ITEM_LOGOUT);
        menulayout.add(mainListBoxMenu);
        add(menulayout);
    }

    private void createGreedWithCars() {
        filterField = new TextField();
        filterField.addValueChangeListener((s) ->


                refreshyourObjectGrid()


        );
        filterField.setValueChangeMode(ValueChangeMode.ON_CHANGE);
        add(filterField);

        grid = new Grid<>();

        grid.setSelectionMode(Grid.SelectionMode.SINGLE);
        //    grid.setItems(cars);
        grid.addColumn(car -> car.getId()).setHeader("Id").setResizable(true);
//        grid.addColumn(car -> car.getPassportData().getRegNumber()).setHeader("Рег.знак").setResizable(true);
//        grid.addColumn(car -> car.getPassportData().getVin()).setHeader("VIN").setResizable(true);
//        grid.addColumn(car -> car.getPassportData().getVin()).setHeader("ГТО до").setResizable(true);
//        grid.addColumn(car -> car.getPassportData().getTypeTS()).setHeader("Тип ТС").setResizable(true);
//        grid.addColumn(car -> car.getPassportData().getModelTS()).setHeader("Модель ТС").setResizable(true);
//        //grid.addColumn(car -> car.getPassportData().getVin()).setHeader("Стрх. до");
//        grid.addColumn(car -> car.getPassportData().getYearOfBuild()).setHeader("Год выпуска").setResizable(true);
//        grid.addColumn(car -> car.getPassportData().getCategory()).setHeader("Категория").setResizable(true);
        grid.addColumn(car -> car.getGeneralData().getPodrazdelenieOrGarage()).setHeader("Подразделение(гараж)").setResizable(true);
        grid.addColumn(car -> car.getGeneralData().getNumberOfGarage()).setHeader("Гаражный номер").setResizable(true);
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
        Panel panel = new Panel();
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
