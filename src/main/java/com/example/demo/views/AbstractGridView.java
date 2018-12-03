package com.example.demo.views;

import com.example.demo.editors.AbstarctEditor;
import com.example.demo.editors.CarEditorG;
import com.example.demo.entity.Selectable;
import com.example.demo.entity.cars.car.Car;
import com.example.demo.services.CarService;
import com.example.demo.services.ItemService;
import com.example.demo.services.search.MyFilterItem;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.provider.ConfigurableFilterDataProvider;
import com.vaadin.flow.data.provider.DataProvider;

import java.util.List;

public abstract class AbstractGridView<T> extends VerticalLayout implements GridInterface {
    protected static final String ADD_BTN_TEXT = "Добавить";
    protected static final String OPEN_BTN_TEXT = "Окрыть";

    protected Class<T> tClass;
    protected ItemService<T> itemService;
    protected Grid<T> grid;
    protected ConfigurableFilterDataProvider<T, Void, MyFilterItem> carVoidVoidConfigurableFilterDataProvider;
    protected MyFilterItem myFilterItem;

    protected Selectable<T> selectedItem;
    protected AbstarctEditor<T> editor;

    public AbstractGridView(ItemService<T> itemService, AbstarctEditor<T> editor, Class<T> type) {
        this.tClass = type;
        this.itemService = itemService;
        this.editor = editor;
        createGrid();
        createBottomMenu();
        setupItems();

    }

    private void createBottomMenu() {
        FlexLayout flexLayout = new FlexLayout();
        Button addBtn = new Button(ADD_BTN_TEXT, VaadinIcon.PLUS.create());
        addBtn.addClickListener((event)->{
            try {
                openEditor(tClass.newInstance());
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });
        Button openBtn = new Button(OPEN_BTN_TEXT, VaadinIcon.FOLDER_OPEN.create());
        openBtn.addClickListener((event)->{
            if(selectedItem != null) {
                openEditor((T) selectedItem);
            }
        });

        flexLayout.add(openBtn,addBtn);
        add(flexLayout);
    }

    protected abstract void createGrid();

    @Override
    public void searchByFilterItem(MyFilterItem myFilterItem) {
        this.myFilterItem = myFilterItem;
        carVoidVoidConfigurableFilterDataProvider.setFilter(myFilterItem);
        grid.getDataProvider().refreshAll();
    }

    @Override
    public Selectable getSelectedItem() {
        return  selectedItem;
    }

    private void setupItems() {
        DataProvider<T, MyFilterItem> dataProvider = DataProvider.fromFilteringCallbacks(
                // First callback fetches items based on a query
                query -> {

                    List<T> cars = itemService.findByExample(query.getFilter(), query.getOffset(), query.getLimit());
                    return cars.stream();
                },
                // Second callback fetches the number of items for a query
                query -> itemService.getCount(query.getFilter()));

        carVoidVoidConfigurableFilterDataProvider = dataProvider.withConfigurableFilter();
        grid.setDataProvider(carVoidVoidConfigurableFilterDataProvider);
    }

    private  void openEditor(T itemT){
        Dialog editorDialog = new Dialog();
        Button save = new Button("Cохранить");
        Button cancel = new Button("Отмена");
        Button delete = new Button("Удалить");
        editorDialog.add(editor);
        editor.getElement().getStyle().set("overflow", "auto");
        FlexLayout submitLayout = new FlexLayout();
        submitLayout.add(save, cancel, delete);
        save.getElement().getThemeList().add("primary");
        delete.getElement().getThemeList().add("error");
        submitLayout.setAlignItems(FlexComponent.Alignment.END);
        editorDialog.add(submitLayout);

        editor.setChangeHandler(() -> {
            editorDialog.close();
            searchByFilterItem(myFilterItem);
            grid.deselectAll();
            selectedItem = null;
        });

        cancel.addClickListener(new ComponentEventListener<ClickEvent<Button>>() {
            @Override
            public void onComponentEvent(ClickEvent<Button> event) {
                editorDialog.close();
                grid.deselectAll();
                selectedItem = null;
            }
        });

        save.addClickListener(event -> {
            editor.save();
        });

        delete.addClickListener(event -> editor.delete());

        editor.edit(itemT);
        editor.setSaveButton(save);
        editorDialog.setHeight("600px");
        editorDialog.setWidth("1200px");
        editorDialog.open();

    }
}