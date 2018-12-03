//package com.example.demo.views;
//
//import com.example.demo.IdViewable;
//import com.example.demo.editors.CarEditorG;
//import com.example.demo.entity.Selectable;
//import com.example.demo.entity.cars.car.*;
//import com.example.demo.services.search.*;
//import com.example.demo.services.serviceImpl.CarServiceImpl;
//import com.vaadin.flow.component.AbstractField;
//import com.vaadin.flow.component.ClickEvent;
//import com.vaadin.flow.component.ComponentEventListener;
//import com.vaadin.flow.component.HasValue;
//import com.vaadin.flow.component.button.Button;
//import com.vaadin.flow.component.combobox.ComboBox;
//import com.vaadin.flow.component.datepicker.DatePicker;
//import com.vaadin.flow.component.dialog.Dialog;
//import com.vaadin.flow.component.grid.Grid;
//import com.vaadin.flow.component.html.Div;
//import com.vaadin.flow.component.icon.VaadinIcon;
//import com.vaadin.flow.component.orderedlayout.FlexComponent;
//import com.vaadin.flow.component.orderedlayout.FlexLayout;
//import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
//import com.vaadin.flow.component.orderedlayout.VerticalLayout;
//import com.vaadin.flow.component.textfield.TextField;
//import com.vaadin.flow.data.provider.ConfigurableFilterDataProvider;
//import com.vaadin.flow.data.provider.DataProvider;
//import com.vaadin.flow.data.selection.SelectionEvent;
//import com.vaadin.flow.data.selection.SelectionListener;
//import com.vaadin.flow.spring.annotation.SpringComponent;
//import com.vaadin.flow.spring.annotation.UIScope;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.time.ZoneId;
//import java.util.Arrays;
//import java.util.Date;
//import java.util.List;
//
//@SpringComponent
//@UIScope
//public class CarView extends VerticalLayout implements IdViewable {
//
//    public Selection selection;
//
//    public final static String ID_VIEW = "CAR_VIEW";
//    private static final String SEARCH_TEXT_PLACEHOLDER = "поиск";
//    private static final String ADD_BTN_TEXT = "Добавить";
//    private static final String OPEN_BTN_TEXT = "Окрыть";
//    private Grid<Car> grid;
//    private ConfigurableFilterDataProvider<Car, Void, MyFilterItem> carVoidVoidConfigurableFilterDataProvider;
//    private HorizontalLayout searchFlexLayout;
//    private ComboBox<EnumColumnNamesForCar> columnNamesComboBox;
//    private TextField searchField = new TextField("Строка поиска", SEARCH_TEXT_PLACEHOLDER);
//    private TextField from = new TextField("От:");
//    private TextField to = new TextField("До:");
//    private DatePicker startDate = new DatePicker("С даты:");
//    private DatePicker finishDate = new DatePicker("По дату:");
//    private ComboBox<EnumYesNo> yesNOComboBox = new ComboBox("Да/Нет:");
//    private ComboBox<EnumTypeFuel> typeFuelComboBox = new ComboBox("Тип топлива:");
//    private ComboBox<Integer> numberComboBox = new ComboBox<>();
//    private Button searchBtn;
//    private Div additionalGreedMenuLayout; // лайяут для доп выбора при поиске
//    private ComboBox<EnumTypeOfBody> typeBodyComboBox = new ComboBox<>("Тип кузова:");
//    private EnumColumnNamesForCar enumColumnNameSearchSelectedForCar = null;
//    private Car selectedCar;
//
//    CarServiceImpl carService;
//    CarEditorG carEditor;
//
//    public CarView(@Autowired CarServiceImpl carService, @Autowired CarEditorG carEditor) {
//        this.carService = carService;
//        this.carEditor = carEditor;
//        createSearchMenu();
//        createGreedWithCars();
//        createBottomMenu();
//        updateListItems();
//    }
//
//    private void createBottomMenu() {
//        FlexLayout flexLayout = new FlexLayout();
//        Button addBtn = new Button(ADD_BTN_TEXT, VaadinIcon.PLUS.create());
//        addBtn.addClickListener((event)->{
//            openEditor(new Car());
//        });
//        Button openBtn = new Button(OPEN_BTN_TEXT, VaadinIcon.FOLDER_OPEN.create());
//        openBtn.addClickListener((event)->{
//            if(selectedCar != null) {
//                openEditor(selectedCar);
//            }
//        });
//
//        flexLayout.add(openBtn,addBtn);
//        add(flexLayout);
//    }
//
//    private void createSearchMenu() {
//        FlexLayout greedMenuLayout = new FlexLayout();
//        FlexLayout searchLayout = new FlexLayout();
//        greedMenuLayout.add(searchLayout);
//
//        searchFlexLayout = new HorizontalLayout();
//        columnNamesComboBox = new ComboBox<>();
//        columnNamesComboBox.setLabel("Выбор критерия:");
//        columnNamesComboBox.setWidth("100%");
//        columnNamesComboBox.setPlaceholder("Поиск по:");
//        columnNamesComboBox.setFilteredItems();
//        columnNamesComboBox.setItems(Arrays.stream(EnumColumnNamesForCar.values()).filter((s) ->
//                s.getVisibleForCombobox()
//        ));
//        searchBtn = new Button(VaadinIcon.SEARCH.create());
//        searchBtn.addClickListener((s) -> {
//            refreshyourObjectGrid();
//        });
//        columnNamesComboBox.addValueChangeListener(new HasValue.ValueChangeListener<AbstractField.ComponentValueChangeEvent<ComboBox<EnumColumnNamesForCar>, EnumColumnNamesForCar>>() {
//            @Override
//            public void valueChanged(AbstractField.ComponentValueChangeEvent<ComboBox<EnumColumnNamesForCar>, EnumColumnNamesForCar> event) {
//                additionalGreedMenuLayout.removeAll();
//                if (event.getValue() != null) {
//                    changeSearchFields(event);
//                } else {
//                    refreshyourObjectGrid();
//                }
//
//            }
//        });
//
//        additionalGreedMenuLayout = new Div();
//        searchFlexLayout.add(columnNamesComboBox, additionalGreedMenuLayout, searchBtn);
//        searchFlexLayout.setAlignItems(FlexComponent.Alignment.BASELINE);
//        greedMenuLayout.add(searchFlexLayout);
//        add(greedMenuLayout);
//    }
//
//    private MyFilterItem getItemFoeSearch(EnumColumnNamesForCar enumColumnNamesForCar) {
//        MyFilterItem myFilterItem = null;
//        switch (enumColumnNamesForCar) {
//            case DATE_OF_TAKE_TO_BALLANCE:
//                if(startDate.getValue() != null && finishDate != null){
//                    Date from = Date.from(
//                            startDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
//
//                    Date to = Date.from(finishDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
//                    myFilterItem = new TwoDateValue(enumColumnNamesForCar);
//                    Datable twoDate = new TwoDate(from, to);
//                    myFilterItem.setDatable(twoDate);
//                }
//                break;
//            case DECOMISSIONED:
//                EnumYesNo value = yesNOComboBox.getValue();
//                if(value != null){
//                    Checkable check = new Check(value.isYes());
//                    myFilterItem = new CheckValue(enumColumnNamesForCar);
//                    myFilterItem.setCheckable(check);
//                }
//
//                break;
//            case DATE_OF_COMMISSIONED:
//                if(startDate.getValue() != null && finishDate != null){
//                    Date from = Date.from(
//                            startDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
//                    Date to = Date.from(finishDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
//                    myFilterItem = new TwoDateValue(enumColumnNamesForCar);
//                    Datable twoDate = new TwoDate(from, to);
//                    myFilterItem.setDatable(twoDate);
//                }
//
//                break;
//            case FAULY:
//                if(yesNOComboBox.getValue() != null){
//                    myFilterItem = new CheckValue(enumColumnNamesForCar);
//                    Checkable check = new Check(yesNOComboBox.getValue().isYes());
//                    myFilterItem.setCheckable(check);
//                }
//                break;
//            case PODRAZDELENIE_OR_GARAGE:
//                if(searchField.getValue() != null){
//                    myFilterItem = new OneTextValue(enumColumnNamesForCar);
//                    Searchable oneTextSearch = new OneTextSearch(searchField.getValue().trim());
//                    myFilterItem.setSearchable(oneTextSearch);
//                }
//                break;
//            case COLONNA:
//                if(searchField.getValue() != null){
//                    myFilterItem = new OneTextValue(enumColumnNamesForCar);
//                    Searchable oneTextSearch = new OneTextSearch(searchField.getValue().trim());
//                    myFilterItem.setSearchable(oneTextSearch);
//                }
//                break;
//            case NUMBER_OF_GARAGE:
//                if(searchField.getValue() != null){
//                    myFilterItem = new OneTextValue(enumColumnNamesForCar);
//                    Searchable oneTextSearch = new OneTextSearch(searchField.getValue().trim());
//                    myFilterItem.setSearchable(oneTextSearch);
//                }
//                break;
//            case NUMBER_OF_INVENTAR:
//                if(searchField.getValue() != null){
//                    myFilterItem = new OneTextValue(enumColumnNamesForCar);
//                    Searchable oneTextSearch = new OneTextSearch(searchField.getValue().trim());
//                    myFilterItem.setSearchable(oneTextSearch);
//                }
//                break;
//            case TYPE_OF_FUEL:
//                if(typeFuelComboBox.getValue() != null){
//                    myFilterItem = new OneTextValue(enumColumnNamesForCar);
//                    Searchable oneTextSearch = new OneTextSearch(typeFuelComboBox.getValue().name());
//                    myFilterItem.setSearchable(oneTextSearch);
//                }
//                break;
//            case MILEAGE:
//                if(from.getValue() != null && to.getValue() != null) {
//                    myFilterItem = new TwoDateValue(enumColumnNamesForCar);
//                    Searchable searchable = new TwoTextSearch(from.getValue().trim(), to.getValue().trim());
//                    myFilterItem.setSearchable(searchable);
//                }
//                break;
//            case MASHINE_HOURS:
//                if(from.getValue() != null && to.getValue() != null) {
//                    myFilterItem = new TwoDateValue(enumColumnNamesForCar);
//                    Searchable searchable = new TwoTextSearch(from.getValue().trim(), to.getValue().trim());
//                    myFilterItem.setSearchable(searchable);
//                }
//                break;
//            case VIN:
//                if(searchField.getValue() != null){
//                    myFilterItem = new OneTextValue(enumColumnNamesForCar);
//                    Searchable oneTextSearch = new OneTextSearch(searchField.getValue().trim());
//                    myFilterItem.setSearchable(oneTextSearch);
//                }
//                break;
//            case TYPE_BODY:
//                if(typeBodyComboBox.getValue() != null){
//                    myFilterItem = new OneTextValue(enumColumnNamesForCar);
//                    Searchable oneTextSearch = new OneTextSearch(typeBodyComboBox.getValue().name());
//                    myFilterItem.setSearchable(oneTextSearch);
//                }
//                break;
//            case YEAR_OF_BUILD:
//                if(startDate.getValue() != null && finishDate != null){
//                    Date from = Date.from(
//                            startDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
//
//                    Date to = Date.from(finishDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
//                    myFilterItem = new TwoDateValue(enumColumnNamesForCar);
//                    Datable twoDate = new TwoDate(from, to);
//                    myFilterItem.setDatable(twoDate);
//                }
//                break;
//            case ECCO_OF_ENGINE:
//                if(numberComboBox.getValue() != null){
//                    myFilterItem = new OneTextValue(enumColumnNamesForCar);
//                    Searchable oneTextSearch = new OneTextSearch(numberComboBox.getValue().toString());
//                    myFilterItem.setSearchable(oneTextSearch);
//                }
//
//                break;
//            case NUMBER_OF_ENGINE:
//                if(searchField.getValue() != null){
//                    myFilterItem = new OneTextValue(enumColumnNamesForCar);
//                    Searchable oneTextSearch = new OneTextSearch(searchField.getValue().trim());
//                    myFilterItem.setSearchable(oneTextSearch);
//                }
//                break;
//            case NUMBER_OF_CHASSIS:
//                if(searchField.getValue() != null){
//                    myFilterItem = new OneTextValue(enumColumnNamesForCar);
//                    Searchable oneTextSearch = new OneTextSearch(searchField.getValue().trim());
//                    myFilterItem.setSearchable(oneTextSearch);
//                }
//                break;
//            case NUMBER_OF_BODY:
//                if(searchField.getValue() != null){
//                    myFilterItem = new OneTextValue(enumColumnNamesForCar);
//                    Searchable oneTextSearch = new OneTextSearch(searchField.getValue().trim());
//                    myFilterItem.setSearchable(oneTextSearch);
//                }
//                break;
//            case POWER_OF_ENGINE:
//                if(from.getValue() != null && to.getValue() != null) {
//                    myFilterItem = new TwoDateValue(enumColumnNamesForCar);
//                    Searchable searchable = new TwoTextSearch(from.getValue().trim(), to.getValue().trim());
//                    myFilterItem.setSearchable(searchable);
//                }
//                break;
//            case VOLUME_OF_ENGINE:
//                if(from.getValue() != null && to.getValue() != null) {
//                    myFilterItem = new TwoDateValue(enumColumnNamesForCar);
//                    Searchable searchable = new TwoTextSearch(from.getValue().trim(), to.getValue().trim());
//                    myFilterItem.setSearchable(searchable);
//                }
//                break;
//            case MAX_MASS:
//                if(from.getValue() != null && to.getValue() != null) {
//                    myFilterItem = new TwoDateValue(enumColumnNamesForCar);
//                    Searchable searchable = new TwoTextSearch(from.getValue().trim(), to.getValue().trim());
//                    myFilterItem.setSearchable(searchable);
//                }
//                break;
//            case MAX_MASS_WITHOUT:
//                if(from.getValue() != null && to.getValue() != null) {
//                    myFilterItem = new TwoDateValue(enumColumnNamesForCar);
//                    Searchable searchable = new TwoTextSearch(from.getValue().trim(), to.getValue().trim());
//                    myFilterItem.setSearchable(searchable);
//                }
//                break;
//            case NUMBER_OF_PASSPORT_TS:
//                if(searchField.getValue() != null){
//                    myFilterItem = new OneTextValue(enumColumnNamesForCar);
//                    Searchable oneTextSearch = new OneTextSearch(searchField.getValue().trim());
//                    myFilterItem.setSearchable(oneTextSearch);
//                }
//                break;
//            case REG_NUMBER:
//                if(searchField.getValue() != null){
//                    myFilterItem = new OneTextValue(enumColumnNamesForCar);
//                    Searchable oneTextSearch = new OneTextSearch(searchField.getValue().trim());
//                    myFilterItem.setSearchable(oneTextSearch);
//                }
//                break;
//            case QUANTITY_OF_PALLET:
//                if(from.getValue() != null && to.getValue() != null) {
//                    myFilterItem = new TwoDateValue(enumColumnNamesForCar);
//                    Searchable searchable = new TwoTextSearch(from.getValue().trim(), to.getValue().trim());
//                    myFilterItem.setSearchable(searchable);
//                }
//                break;
//            case WIDHT_OF_BODY:
//                if(from.getValue() != null && to.getValue() != null) {
//                    myFilterItem = new TwoDateValue(enumColumnNamesForCar);
//                    Searchable searchable = new TwoTextSearch(from.getValue().trim(), to.getValue().trim());
//                    myFilterItem.setSearchable(searchable);
//                }
//                break;
//            case HEIGHT_OF_BODY:
//                if(from.getValue() != null && to.getValue() != null) {
//                    myFilterItem = new TwoDateValue(enumColumnNamesForCar);
//                    Searchable searchable = new TwoTextSearch(from.getValue().trim(), to.getValue().trim());
//                    myFilterItem.setSearchable(searchable);
//                }
//                break;
//            case LENGHT_OF_BODY:
//                if(from.getValue() != null && to.getValue() != null) {
//                    myFilterItem = new TwoDateValue(enumColumnNamesForCar);
//                    Searchable searchable = new TwoTextSearch(from.getValue().trim(), to.getValue().trim());
//                    myFilterItem.setSearchable(searchable);
//                }
//                break;
//            case VOLUME_OF_BODY:
//                if(from.getValue() != null && to.getValue() != null) {
//                    myFilterItem = new TwoDateValue(enumColumnNamesForCar);
//                    Searchable searchable = new TwoTextSearch(from.getValue().trim(), to.getValue().trim());
//                    myFilterItem.setSearchable(searchable);
//                }
//                break;
//            default:
//                System.out.println("Дефолтное значение");
//        }
//        return myFilterItem;
//
//    }
//
//
//    private void changeSearchFields(AbstractField.ComponentValueChangeEvent<ComboBox<EnumColumnNamesForCar>, EnumColumnNamesForCar> event) {
//        String label = event.getValue().getDisplayName() + ":";
//        searchField.setLabel(label);
//        yesNOComboBox.setLabel(label);
//        switch (event.getValue()) {
//            case DATE_OF_TAKE_TO_BALLANCE:
//                additionalGreedMenuLayout.add(startDate, finishDate);
//                break;
//            case DECOMISSIONED:
//                yesNOComboBox.setItems(EnumYesNo.values());
//                additionalGreedMenuLayout.add(yesNOComboBox);
//                break;
//            case DATE_OF_COMMISSIONED:
//                additionalGreedMenuLayout.add(startDate, finishDate);
//                break;
//            case FAULY:
//                yesNOComboBox.setItems(EnumYesNo.values());
//                additionalGreedMenuLayout.add(yesNOComboBox);
//                break;
//            case PODRAZDELENIE_OR_GARAGE:
//                additionalGreedMenuLayout.add(searchField);
//                break;
//            case COLONNA:
//                additionalGreedMenuLayout.add(searchField);
//                break;
//            case NUMBER_OF_GARAGE:
//                additionalGreedMenuLayout.add(searchField);
//                break;
//            case NUMBER_OF_INVENTAR:
//                additionalGreedMenuLayout.add(searchField);
//                break;
//            case TYPE_OF_FUEL:
//                typeFuelComboBox.setItems(EnumTypeFuel.values());
//                additionalGreedMenuLayout.add(typeFuelComboBox);
//                break;
//            case MILEAGE:
//                additionalGreedMenuLayout.add(from, to);
//                break;
//            case MASHINE_HOURS:
//                additionalGreedMenuLayout.add(from, to);
//                break;
//            case VIN:
//                additionalGreedMenuLayout.add(searchField);
//                break;
//            case TYPE_BODY:
//                typeBodyComboBox.setItems(EnumTypeOfBody.values());
//                additionalGreedMenuLayout.add(typeBodyComboBox);
//                break;
//            case YEAR_OF_BUILD:
//                additionalGreedMenuLayout.add(startDate, finishDate);
//                break;
//            case ECCO_OF_ENGINE:
//                numberComboBox.setLabel(label);
//                numberComboBox.setItems(1, 2, 3, 4, 5);
//                additionalGreedMenuLayout.add(numberComboBox);
//                break;
//            case NUMBER_OF_ENGINE:
//                additionalGreedMenuLayout.add(searchField);
//                break;
//            case NUMBER_OF_CHASSIS:
//                additionalGreedMenuLayout.add(searchField);
//                break;
//            case NUMBER_OF_BODY:
//                additionalGreedMenuLayout.add(searchField);
//                break;
//            case POWER_OF_ENGINE:
//                additionalGreedMenuLayout.add(from, to);
//                break;
//            case VOLUME_OF_ENGINE:
//                additionalGreedMenuLayout.add(from, to);
//                break;
//            case MAX_MASS:
//                additionalGreedMenuLayout.add(from, to);
//                break;
//            case MAX_MASS_WITHOUT:
//                additionalGreedMenuLayout.add(from, to);
//                break;
//            case NUMBER_OF_PASSPORT_TS:
//                additionalGreedMenuLayout.add(searchField);
//                break;
//            case REG_NUMBER:
//                additionalGreedMenuLayout.add(searchField);
//                break;
//            case QUANTITY_OF_PALLET:
//                additionalGreedMenuLayout.add(from, to);
//                break;
//            case WIDHT_OF_BODY:
//                additionalGreedMenuLayout.add(from, to);
//                break;
//            case HEIGHT_OF_BODY:
//                additionalGreedMenuLayout.add(from, to);
//                break;
//            case LENGHT_OF_BODY:
//                additionalGreedMenuLayout.add(from, to);
//                break;
//            case VOLUME_OF_BODY:
//                additionalGreedMenuLayout.add(from, to);
//                break;
//            default:
//                System.out.println("Дефолтное значение");
//        }
//        searchFlexLayout.setAlignItems(FlexComponent.Alignment.BASELINE);
//
//    }
//
//
//    private void updateListItems() {
//        DataProvider<Car, MyFilterItem> dataProvider = DataProvider.fromFilteringCallbacks(
//                // First callback fetches items based on a query
//                query -> {
//
//                    List<Car> cars = carService.findByExample(query.getFilter(), query.getOffset(), query.getLimit());
//                    return cars.stream();
//                },
//                // Second callback fetches the number of items for a query
//                query -> carService.getCount(query.getFilter()));
//
//        carVoidVoidConfigurableFilterDataProvider = dataProvider.withConfigurableFilter();
//        grid.setDataProvider(carVoidVoidConfigurableFilterDataProvider);
//    }
//
//
//    private void refreshyourObjectGrid() {
//        enumColumnNameSearchSelectedForCar = columnNamesComboBox.getValue();
//        MyFilterItem myFilterItem = null;
//
//        if (enumColumnNameSearchSelectedForCar != null) {
//            myFilterItem = getItemFoeSearch(enumColumnNameSearchSelectedForCar);
//        }
//
//        carVoidVoidConfigurableFilterDataProvider.setFilter(myFilterItem);
//        grid.getDataProvider().refreshAll();
//    }
//
//    private void createGreedWithCars() {
//        grid = new Grid<>();
//
//        grid.setSelectionMode(Grid.SelectionMode.SINGLE);
//        //    grid.setItems(cars);;
//        // grid.addColumn(car -> car.getId(), "id").setResizable(true).setHeader(getSearchFieldAndHeader("id"));
//        grid.addColumn(car -> car.getId()).setHeader("ID").setResizable(true);
//        grid.addColumn(car -> car.getPassportData().getRegNumber()).setHeader("Рег.знак").setResizable(true);
//        grid.addColumn(car -> car.getPassportData().getVin()).setHeader("VIN").setResizable(true);
//        grid.addColumn(car -> car.getPassportData().getTypeTS()).setHeader("Тип ТС").setResizable(true);
//        grid.addColumn(car -> car.getGeneralData().getNumberOfGarage()).setHeader("Номер Гаража").setResizable(true);
//        grid.addColumn(car -> car.getGeneralData().getComment()).setHeader("Комментарий").setResizable(true);
//        grid.addColumn(car -> car.getGeneralData().getMileage()).setHeader("Пробег").setResizable(true);
//        grid.addColumn(car -> car.getGeneralData().getDateOfTakeToBalanse()).setHeader("Дата принятия на баланс").setResizable(true);
//        grid.addColumn(car -> car.getPassportData().getEccoClass()).setHeader("ЭКО клас");
//        grid.addColumn(car -> car.isTrack() == true ? "Прицеп" : "Транспорт").setHeader("Тип");
//
//        grid.getSelectionModel().addSelectionListener(new SelectionListener<Grid<Car>, Car>() {
//            @Override
//            public void selectionChange(SelectionEvent<Grid<Car>, Car> event) {
//                boolean somethingSelected = !grid.getSelectedItems().isEmpty();
//                if (somethingSelected) {
//                    selectedCar = event.getFirstSelectedItem().get();
//                    selection.selectItem(selectedCar);
//                    //openEditor(event.getFirstSelectedItem().get());
//                }
//            }
//        });
//
//        add(grid);
//    }
//
//    private void openEditor(Car car) {
//        Dialog editorDialog = new Dialog();
//        Button save = new Button("Cохранить");
//        Button cancel = new Button("Отмена");
//        Button delete = new Button("Удалить");
//        editorDialog.add(carEditor);
//        carEditor.getElement().getStyle().set("overflow", "auto");
//        FlexLayout submitLayout = new FlexLayout();
//        submitLayout.add(save, cancel, delete);
//        save.getElement().getThemeList().add("primary");
//        delete.getElement().getThemeList().add("error");
//        submitLayout.setAlignItems(FlexComponent.Alignment.END);
//        editorDialog.add(submitLayout);
//
//        carEditor.setChangeHandler(() -> {
//            editorDialog.close();
//            refreshyourObjectGrid();
//            grid.deselectAll();
//            selectedCar = null;
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
//            carEditor.save();
//        });
//
//        delete.addClickListener(event -> carEditor.delete());
//
//        carEditor.edit(car);
//        carEditor.setSaveButton(save);
//        editorDialog.setHeight("600px");
//        editorDialog.setWidth("1200px");
//        editorDialog.open();
//    }
//
//    @Override
//    public String getIdView() {
//        return ID_VIEW;
//    }
//}
