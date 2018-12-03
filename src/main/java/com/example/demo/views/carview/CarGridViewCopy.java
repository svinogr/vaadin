package com.example.demo.views.carview;

import com.example.demo.editors.CarEditorG;
import com.example.demo.entity.Selectable;
import com.example.demo.entity.cars.car.Car;
import com.example.demo.services.CarService;
import com.example.demo.services.search.MyFilterItem;
import com.example.demo.views.GridInterface;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.provider.ConfigurableFilterDataProvider;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.selection.SelectionEvent;
import com.vaadin.flow.data.selection.SelectionListener;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;

import java.util.List;

@SpringComponent
@UIScope
public class CarGridViewCopy extends VerticalLayout implements GridInterface {
    private static final String ADD_BTN_TEXT = "Добавить";
    private static final String OPEN_BTN_TEXT = "Окрыть";

    private CarService carService;
    private Grid<Car> grid;
    private ConfigurableFilterDataProvider<Car, Void, MyFilterItem> carVoidVoidConfigurableFilterDataProvider;
    private MyFilterItem myFilterItem;

    private Car selectedCar;
    private CarEditorG carEditor;
    public CarGridViewCopy(CarService carService, CarEditorG carEditor) {
        this.carService = carService;
        this.carEditor = carEditor;
        createGrid();
        createBottomMenu();
        setupItems();
    }

    private void createBottomMenu() {
        FlexLayout flexLayout = new FlexLayout();
        Button addBtn = new Button(ADD_BTN_TEXT, VaadinIcon.PLUS.create());
        addBtn.addClickListener((event)->{
            openEditor(new Car());
        });
        Button openBtn = new Button(OPEN_BTN_TEXT, VaadinIcon.FOLDER_OPEN.create());
        openBtn.addClickListener((event)->{
            if(selectedCar != null) {
                openEditor(selectedCar);
            }
        });

        flexLayout.add(openBtn,addBtn);
        add(flexLayout);
    }

    private void createGrid() {
        grid = new Grid();
        grid.setSelectionMode(Grid.SelectionMode.SINGLE);
        grid.addColumn(car -> car.getId()).setHeader("ID").setResizable(true);
        grid.addColumn(car -> car.getPassportData().getRegNumber()).setHeader("Рег.знак").setResizable(true);
        grid.addColumn(car -> car.getPassportData().getVin()).setHeader("VIN").setResizable(true);
        grid.addColumn(car -> car.getPassportData().getTypeTS()).setHeader("Тип ТС").setResizable(true);
        grid.addColumn(car -> car.getGeneralData().getNumberOfGarage()).setHeader("Номер Гаража").setResizable(true);
        grid.addColumn(car -> car.getGeneralData().getComment()).setHeader("Комментарий").setResizable(true);
        grid.addColumn(car -> car.getGeneralData().getMileage()).setHeader("Пробег").setResizable(true);
        grid.addColumn(car -> car.getGeneralData().getDateOfTakeToBalanse()).setHeader("Дата принятия на баланс").setResizable(true);
        grid.addColumn(car -> car.getPassportData().getEccoClass()).setHeader("ЭКО клас");
        grid.addColumn(car -> car.isTrack() == true ? "Прицеп" : "Транспорт").setHeader("Тип");

        grid.getSelectionModel().addSelectionListener(new SelectionListener<Grid<Car>, Car>() {
            @Override
            public void selectionChange(SelectionEvent<Grid<Car>, Car> event) {
                boolean somethingSelected = !grid.getSelectedItems().isEmpty();
                if (somethingSelected) {
                    selectedCar = event.getFirstSelectedItem().get();
                }
            }
        });

        add(grid);
    }

    private  void openEditor(Car car){
        Dialog editorDialog = new Dialog();
        Button save = new Button("Cохранить");
        Button cancel = new Button("Отмена");
        Button delete = new Button("Удалить");
        editorDialog.add(carEditor);
        carEditor.getElement().getStyle().set("overflow", "auto");
        FlexLayout submitLayout = new FlexLayout();
        submitLayout.add(save, cancel, delete);
        save.getElement().getThemeList().add("primary");
        delete.getElement().getThemeList().add("error");
        submitLayout.setAlignItems(Alignment.END);
        editorDialog.add(submitLayout);

        carEditor.setChangeHandler(() -> {
            editorDialog.close();
            searchByFilterItem(myFilterItem);
            grid.deselectAll();
            selectedCar = null;
        });

        cancel.addClickListener(new ComponentEventListener<ClickEvent<Button>>() {
            @Override
            public void onComponentEvent(ClickEvent<Button> event) {
                editorDialog.close();
            }
        });

        save.addClickListener(event -> {
            carEditor.save();
        });

        delete.addClickListener(event -> carEditor.delete());

        carEditor.edit(car);
        carEditor.setSaveButton(save);
        editorDialog.setHeight("600px");
        editorDialog.setWidth("1200px");
        editorDialog.open();

    }

    private void setupItems() {
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

    @Override
    public void searchByFilterItem(MyFilterItem myFilterItem) {
        this.myFilterItem = myFilterItem;
        carVoidVoidConfigurableFilterDataProvider.setFilter(myFilterItem);
        grid.getDataProvider().refreshAll();
    }

    @Override
    public Selectable getSelectedItem() {
        return selectedCar;
    }
}
