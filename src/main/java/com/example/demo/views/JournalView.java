//package com.example.demo.views;
//
//import com.example.demo.IdViewable;
//import com.example.demo.editors.JournalEditor;
//import com.example.demo.entity.jornal.JournalItem;
//import com.example.demo.services.JournalService;
//import com.vaadin.flow.component.ClickEvent;
//import com.vaadin.flow.component.ComponentEventListener;
//import com.vaadin.flow.component.button.Button;
//import com.vaadin.flow.component.dependency.HtmlImport;
//import com.vaadin.flow.component.dialog.Dialog;
//import com.vaadin.flow.component.grid.Grid;
//import com.vaadin.flow.component.icon.VaadinIcon;
//import com.vaadin.flow.component.orderedlayout.FlexComponent;
//import com.vaadin.flow.component.orderedlayout.FlexLayout;
//import com.vaadin.flow.component.orderedlayout.VerticalLayout;
//import com.vaadin.flow.data.provider.ConfigurableFilterDataProvider;
//import com.vaadin.flow.data.provider.DataProvider;
//import com.vaadin.flow.data.selection.SelectionEvent;
//import com.vaadin.flow.data.selection.SelectionListener;
//import com.vaadin.flow.spring.annotation.SpringComponent;
//import com.vaadin.flow.spring.annotation.UIScope;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.util.List;
//
//@SpringComponent
//@UIScope
//@HtmlImport("styles/styles.html")
//public class JournalView extends VerticalLayout implements IdViewable {
//    private static final String BACK_ROUT = "login";
//    public static final String ID_VIEW = "JOURNAL_VIEW";
//
//    // private static final String SEARCH_TEXT_PLACEHOLDER = "поиск";
//    private static final String ADD_BTN_TEXT = "Добавить";
//    private static final String OPEN_BTN_TEXT = "Окрыть";
//    private Grid<JournalItem> grid;
//    private ConfigurableFilterDataProvider<JournalItem, Void, Void> carVoidVoidConfigurableFilterDataProvider;
//    // private HorizontalLayout searchFlexLayout;
//    //private ComboBox<EnumColumnNamesForCar> columnNamesComboBox;
//    // private TextField searchField = new TextField("Строка поиска", SEARCH_TEXT_PLACEHOLDER);
//    //  private TextField from = new TextField("От:");
//    // private TextField to = new TextField("До:");
//    //  private DatePicker startDate = new DatePicker("С даты:");
//    //   private DatePicker finishDate = new DatePicker("По дату:");
//    // private ComboBox<EnumYesNo> yesNOComboBox = new ComboBox("Да/Нет:");
//    // private ComboBox<EnumTypeFuel> typeFuelComboBox = new ComboBox("Тип топлива:");
//    //  private ComboBox<Integer> numberComboBox = new ComboBox<>();
//    //  private Button searchBtn;
//    // private Div additionalGreedMenuLayout; // лайяут для доп выбора при поиске
//    //  private ComboBox<EnumTypeOfBody> typeBodyComboBox = new ComboBox<>("Тип кузова:");
//    //  private EnumColumnNamesForCar enumColumnNameSearchSelected = null;
//    //  private Car selectedCar;
//
//    JournalService journalService;
//    JournalEditor journalEditor;
//    private JournalItem selectedJournalItem;
//    private long parentId;
//
//    public JournalView(@Autowired JournalService journalService
////            , @Autowired JournalEditor journalEditor
//    ) {
//        this.journalService = journalService;
//     //   this.journalEditor = journalEditor;
//        createGreedWithJournal();
//        createBottomMenu();
//        //updateListItems();
//    }
//
//    private void createBottomMenu() {
//        FlexLayout flexLayout = new FlexLayout();
//        Button addBtn = new Button(ADD_BTN_TEXT, VaadinIcon.PLUS.create());
//        addBtn.addClickListener((event) -> {
//            JournalItem journalItem = new JournalItem();
//            journalItem.setCar_id(parentId);
//            openEditor(journalItem);
//        });
//
//        Button openBtn = new Button(OPEN_BTN_TEXT, VaadinIcon.FOLDER_OPEN.create());
//        openBtn.addClickListener((event) -> {
//            if (selectedJournalItem != null) {
//                openEditor(selectedJournalItem);
//            }
//        });
//
//        flexLayout.add(openBtn, addBtn);
//        add(flexLayout);
//    }
//
//
//    public void updateListItems(long id) {
//        parentId = id;
//        DataProvider<JournalItem, Void> dataProvider = DataProvider.fromCallbacks(
//                query -> {
//                    List<JournalItem> journalItems = journalService.findAllByParentId(parentId, query.getOffset(), query.getLimit());
//                    return journalItems.stream();
//                },
//                query -> {
//                    return journalService.getCountByParentId(parentId);
//                }
//        );
//        grid.setDataProvider(dataProvider);
//    }
//
//    private void createGreedWithJournal() {
//        grid = new Grid<>();
//
//        grid.setSelectionMode(Grid.SelectionMode.SINGLE);
//
//        grid.addColumn(journalItem -> String.valueOf(journalItem.getId())).setHeader("ID").setResizable(true);
//        grid.addColumn(journalItem -> journalItem.getEnumTypeRecord()).setHeader("Тип записи").setResizable(true);
//
//        grid.getSelectionModel().addSelectionListener(new SelectionListener<Grid<JournalItem>, JournalItem>() {
//            @Override
//            public void selectionChange(SelectionEvent<Grid<JournalItem>, JournalItem> event) {
//                boolean somethingSelected = !grid.getSelectedItems().isEmpty();
//                if (somethingSelected) {
//                    selectedJournalItem = event.getFirstSelectedItem().get();
//                }
//            }
//        });
//
//        add(grid);
//    }
//
//    private void openEditor(JournalItem journalItem) {
//        Dialog editorDialog = new Dialog();
//        Button save = new Button("Cохранить");
//        Button cancel = new Button("Отмена");
//        Button delete = new Button("Удалить");
//        // здесь в отличие от кар едитора использую сощдание нового едитора, так как при енжекте какая то хрень с обновлением внутренностей его
//        journalEditor = new JournalEditor(journalService);
//        editorDialog.add(journalEditor);
//        journalEditor.getElement().getStyle().set("overflow", "auto");
//        FlexLayout submitLayout = new FlexLayout();
//        submitLayout.add(save, cancel, delete);
//        save.getElement().getThemeList().add("primary");
//        delete.getElement().getThemeList().add("error");
//        submitLayout.setAlignItems(FlexComponent.Alignment.END);
//        editorDialog.add(submitLayout);
//
//        journalEditor.setChangeHandler(() -> {
//            editorDialog.close();
//            updateListItems(parentId);
//            grid.deselectAll();
//            selectedJournalItem = null;
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
//            journalEditor.save();
//        });
//
//        delete.addClickListener(event -> journalEditor.delete());
//
//        journalEditor.edit(journalItem);
//        journalEditor.setSaveButton(save);
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
