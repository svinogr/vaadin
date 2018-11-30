package com.example.demo.views;

import com.example.demo.IdViewable;
import com.example.demo.editors.OrganisationEditorG;
import com.example.demo.entity.organisation.EnumColumnNameForOrg;
import com.example.demo.entity.organisation.Organisation;
import com.example.demo.services.OrganisationService;
import com.example.demo.services.search.*;
import com.vaadin.flow.component.AbstractField;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.HasValue;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.HtmlImport;
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

import java.util.Arrays;
import java.util.List;

@SpringComponent
@UIScope
@HtmlImport("styles/styles.html")
public class OrganisationView extends VerticalLayout implements IdViewable {
    public final static String ID_VIEW = "ORGANISATION_VIEW";
    private static final String SEARCH_TEXT_PLACEHOLDER = "поиск";
    private static final String ADD_BTN_TEXT = "Добавить";
    private static final String OPEN_BTN_TEXT = "Окрыть";
    private Grid<Organisation> grid;
    private ConfigurableFilterDataProvider<Organisation, Void, MyFilterItem> carVoidVoidConfigurableFilterDataProvider;
    private HorizontalLayout searchFlexLayout;
    private ComboBox<EnumColumnNameForOrg> columnNamesComboBox;
    private TextField searchField = new TextField("Строка поиска", SEARCH_TEXT_PLACEHOLDER);
  //  private TextField from = new TextField("От:");
   // private TextField to = new TextField("До:");
    private DatePicker startDate = new DatePicker("С даты:");
    private DatePicker finishDate = new DatePicker("По дату:");
  //  private ComboBox<EnumYesNo> yesNOComboBox = new ComboBox("Да/Нет:");
  //  private ComboBox<Integer> numberComboBox = new ComboBox<>();
    private Button searchBtn;
    private Div additionalGreedMenuLayout; // лайяут для доп выбора при поиске
    private EnumColumnNameForOrg enumColumnNameSearchSelectedForOrg = null;
    private Organisation selecteOrganisation;

    OrganisationService organisationService;
    OrganisationEditorG organisationEditor;

    public OrganisationView(@Autowired OrganisationService organisationService, @Autowired OrganisationEditorG organisationEditor) {
        this.organisationService = organisationService;
        this.organisationEditor = organisationEditor;
        createSearchMenu();
        createGreedWithCars();
        createBottomMenu();
        updateListItems();
    }

    private void createBottomMenu() {
        FlexLayout flexLayout = new FlexLayout();
        Button addBtn = new Button(ADD_BTN_TEXT, VaadinIcon.PLUS.create());
        addBtn.addClickListener((event) -> {
            openEditor(new Organisation());
        });
        Button openBtn = new Button(OPEN_BTN_TEXT, VaadinIcon.FOLDER_OPEN.create());
        openBtn.addClickListener((event) -> {
            if (selecteOrganisation != null) {
                openEditor(selecteOrganisation);
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
        columnNamesComboBox.setItems(Arrays.stream(EnumColumnNameForOrg.values()).filter((s) ->
                s.getVisibleForCombobox()
        ));
        searchBtn = new Button(VaadinIcon.SEARCH.create());
        searchBtn.addClickListener((s) -> {
            refreshyourObjectGrid();
        });
        columnNamesComboBox.addValueChangeListener(new HasValue.ValueChangeListener<AbstractField.ComponentValueChangeEvent<ComboBox<EnumColumnNameForOrg>, EnumColumnNameForOrg>>() {
            @Override
            public void valueChanged(AbstractField.ComponentValueChangeEvent<ComboBox<EnumColumnNameForOrg>, EnumColumnNameForOrg> event) {
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

    private MyFilterItem getItemFoeSearch(EnumColumnNameForOrg enumColumnNameForOrg) {
        MyFilterItem myFilterItem = null;
        switch (enumColumnNameForOrg) {
            case NAME:
                if (searchField.getValue() != null) {
                    myFilterItem = new OneTextValue(enumColumnNameForOrg);
                    Searchable oneTextSearch = new OneTextSearch(searchField.getValue().trim());
                    myFilterItem.setSearchable(oneTextSearch);
                }
                break;
            case OGRN:
                if (searchField.getValue() != null) {
                    myFilterItem = new OneTextValue(enumColumnNameForOrg);
                    Searchable oneTextSearch = new OneTextSearch(searchField.getValue().trim());
                    myFilterItem.setSearchable(oneTextSearch);
                }
                break;
            case OKPO:
                if (searchField.getValue() != null) {
                    myFilterItem = new OneTextValue(enumColumnNameForOrg);
                    Searchable oneTextSearch = new OneTextSearch(searchField.getValue().trim());
                    myFilterItem.setSearchable(oneTextSearch);
                }
                break;
            case INN:
                if (searchField.getValue() != null) {
                    myFilterItem = new OneTextValue(enumColumnNameForOrg);
                    Searchable oneTextSearch = new OneTextSearch(searchField.getValue().trim());
                    myFilterItem.setSearchable(oneTextSearch);
                }
                break;
            case EGRUL:
                if (searchField.getValue() != null) {
                    myFilterItem = new OneTextValue(enumColumnNameForOrg);
                    Searchable oneTextSearch = new OneTextSearch(searchField.getValue().trim());
                    myFilterItem.setSearchable(oneTextSearch);
                }
                break;
            case KPP:
                if (searchField.getValue() != null) {
                    myFilterItem = new OneTextValue(enumColumnNameForOrg);
                    Searchable oneTextSearch = new OneTextSearch(searchField.getValue().trim());
                    myFilterItem.setSearchable(oneTextSearch);
                }
                break;
            default:
                System.out.println("Дефолтное значение");
        }
        return myFilterItem;

    }


    private void changeSearchFields(AbstractField.ComponentValueChangeEvent<ComboBox<EnumColumnNameForOrg>, EnumColumnNameForOrg> event) {
        String label = event.getValue().getDisplayName() + ":";
        searchField.setLabel(label);
       // yesNOComboBox.setLabel(label);
       // typePersonComboBox = new ComboBox<>();
        switch (event.getValue()) {
            case NAME:
                additionalGreedMenuLayout.add(searchField);
                break;
            case OGRN:
                additionalGreedMenuLayout.add(searchField);
                break;
            case OKPO:
                additionalGreedMenuLayout.add(searchField);
                break;
            case INN:
                additionalGreedMenuLayout.add(searchField);
                break;
            case EGRUL:
                additionalGreedMenuLayout.add(searchField);
                break;
            case KPP:
                additionalGreedMenuLayout.add(searchField);
                break;
            default:
                System.out.println("Дефолтное значение");
        }
        searchFlexLayout.setAlignItems(FlexComponent.Alignment.BASELINE);

    }


    private void updateListItems() {
        DataProvider<Organisation, MyFilterItem> dataProvider = DataProvider.fromFilteringCallbacks(
                // First callback fetches items based on a query
                query -> {

                    List<Organisation> cars = organisationService.findByExample(query.getFilter(), query.getOffset(), query.getLimit());
                    return cars.stream();
                },
                // Second callback fetches the number of items for a query
                query -> organisationService.getCount(query.getFilter()));

        carVoidVoidConfigurableFilterDataProvider = dataProvider.withConfigurableFilter();
        grid.setDataProvider(carVoidVoidConfigurableFilterDataProvider);
    }


    private void refreshyourObjectGrid() {
        enumColumnNameSearchSelectedForOrg = columnNamesComboBox.getValue();
        MyFilterItem myFilterItem = null;

        if (enumColumnNameSearchSelectedForOrg != null) {
            myFilterItem = getItemFoeSearch(enumColumnNameSearchSelectedForOrg);
        }

        carVoidVoidConfigurableFilterDataProvider.setFilter(myFilterItem);
        grid.getDataProvider().refreshAll();
    }

    private void createGreedWithCars() {
        grid = new Grid<>();
        grid.setSelectionMode(Grid.SelectionMode.SINGLE);
        grid.addColumn(organisation -> organisation.getId()).setHeader("ID").setResizable(true);
        grid.addColumn(organisation -> organisation.getName()).setHeader("Имя").setResizable(true);
        grid.addColumn(organisation -> organisation.getPhone()).setHeader("Телефон").setResizable(true);
        grid.addColumn(organisation -> organisation.getInn()).setHeader("ИНН").setResizable(true);
        grid.addColumn(organisation -> organisation.getOgrn()).setHeader("ОГРН").setResizable(true);

        grid.getSelectionModel().addSelectionListener(new SelectionListener<Grid<Organisation>, Organisation>() {
            @Override
            public void selectionChange(SelectionEvent<Grid<Organisation>, Organisation> event) {
                boolean somethingSelected = !grid.getSelectedItems().isEmpty();
                if (somethingSelected) {
                    selecteOrganisation = event.getFirstSelectedItem().get();

                    // selection.selectItem(selecteOrganisation);
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

    private void openEditor(Organisation organisation) {
        Dialog editorDialog = new Dialog();
        Button save = new Button("Cохранить");
        Button cancel = new Button("Отмена");
        Button delete = new Button("Удалить");
        editorDialog.add(organisationEditor);
        organisationEditor.getElement().getStyle().set("overflow", "auto");
        FlexLayout submitLayout = new FlexLayout();
        submitLayout.add(save, cancel, delete);
        save.getElement().getThemeList().add("primary");
        delete.getElement().getThemeList().add("error");
        submitLayout.setAlignItems(FlexComponent.Alignment.END);
        editorDialog.add(submitLayout);

        organisationEditor.setChangeHandler(() -> {
            editorDialog.close();
            refreshyourObjectGrid();
            grid.deselectAll();
            selecteOrganisation = null;
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
            organisationEditor.save();
        });

        delete.addClickListener(event -> organisationEditor.delete());

        organisationEditor.edit(organisation);
        organisationEditor.setSaveButton(save);
        editorDialog.setHeight("600px");
        editorDialog.setWidth("1200px");
        editorDialog.open();
    }


}
