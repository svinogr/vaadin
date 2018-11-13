package com.example.demo;

import com.example.demo.entity.cars.car.*;
import com.example.demo.entity.cars.utils.search.*;
import com.example.demo.entity.jornal.JournalItem;
import com.vaadin.flow.component.AbstractField;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.HasValue;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.provider.ConfigurableFilterDataProvider;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.selection.SelectionEvent;
import com.vaadin.flow.data.selection.SelectionListener;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import jdk.nashorn.internal.scripts.JO;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.sun.tools.internal.xjc.reader.Ring.add;
@SpringComponent
@UIScope
@HtmlImport("styles/styles.html")
public class JournalView  extends VerticalLayout implements  IdViewable{
    private static final String BACK_ROUT = "login";
    public static  final   String ID_VIEW = "JOURNAL_VIEW";

   // private static final String SEARCH_TEXT_PLACEHOLDER = "поиск";
    private static final String ADD_BTN_TEXT = "Добавить";
    private static final String OPEN_BTN_TEXT = "Окрыть";
    private Grid<JournalItem> grid;
    private ConfigurableFilterDataProvider<JournalItem, Void, Void> carVoidVoidConfigurableFilterDataProvider;
   // private HorizontalLayout searchFlexLayout;
    //private ComboBox<EnumColumnNames> columnNamesComboBox;
   // private TextField searchField = new TextField("Строка поиска", SEARCH_TEXT_PLACEHOLDER);
  //  private TextField from = new TextField("От:");
   // private TextField to = new TextField("До:");
  //  private DatePicker startDate = new DatePicker("С даты:");
 //   private DatePicker finishDate = new DatePicker("По дату:");
   // private ComboBox<EnumYesNo> yesNOComboBox = new ComboBox("Да/Нет:");
   // private ComboBox<EnumTypeFuel> typeFuelComboBox = new ComboBox("Тип топлива:");
  //  private ComboBox<Integer> numberComboBox = new ComboBox<>();
  //  private Button searchBtn;
   // private Div additionalGreedMenuLayout; // лайяут для доп выбора при поиске
  //  private ComboBox<EnumTypeOfBody> typeBodyComboBox = new ComboBox<>("Тип кузова:");
  //  private EnumColumnNames enumColumnNameSearchSelected = null;
  //  private Car selectedCar;

    JornalService jornalService;
    JornalEditor jornalEditor;
    private JournalItem selectedJournalItem;
    private long parentId;

    public CarView(@Autowired JornalService jornalService, @Autowired JornalEditor jornalEditor) {
        this.jornalService = jornalService;
        this.jornalEditor = jornalEditor;
        createGreedWithJournal();
        createBottomMenu();
        updateListItems();
    }

    private void createBottomMenu() {
        FlexLayout flexLayout = new FlexLayout();
        Button addBtn = new Button(ADD_BTN_TEXT, VaadinIcon.PLUS.create());
        addBtn.addClickListener((event)->{
            openEditor(new Car());
        });
        Button openBtn = new Button(OPEN_BTN_TEXT, VaadinIcon.FOLDER_OPEN.create());
        openBtn.addClickListener((event)->{
            if(selectedJournalItem != null) {
                openEditor(selectedJournalItem);
            }
        });

        flexLayout.add(openBtn,addBtn);
        add(flexLayout);
    }


    private void updateListItems(long id) {
        parentId = id;

        grid.setDataProvider((setOrder, offset, limit )->{
            List<JournalItem> journalItems = jornalService.findAllByParentId(parentId, offset, limit);
            return journalItems.stream();
        }, ()->{return jornalService.getCountByParentId(parentId);});
    }

    private void createGreedWithJournal() {
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
        grid.getSelectionModel().addSelectionListener(new SelectionListener<Grid<JournalItem>, JournalItem>() {
            @Override
            public void selectionChange(SelectionEvent<Grid<JournalItem>, JournalItem> event) {
                boolean somethingSelected = !grid.getSelectedItems().isEmpty();
                if (somethingSelected) {
                    selectedJournalItem = event.getFirstSelectedItem().get();

                    //openEditor(event.getFirstSelectedItem().get());
                }
            }
        });


        add(grid);
    }

    private void openEditor(Car car) {
        Dialog editorDialog = new Dialog();
        Button save = new Button("Cохранить");
        Button cancel = new Button("Отмена");
        Button delete = new Button("Удалить");
        editorDialog.add(jornalEditor);
        jornalEditor.getElement().getStyle().set("overflow", "auto");
        FlexLayout submitLayout = new FlexLayout();
        submitLayout.add(save, cancel, delete);
        save.getElement().getThemeList().add("primary");
        delete.getElement().getThemeList().add("error");
        submitLayout.setAlignItems(FlexComponent.Alignment.END);
        editorDialog.add(submitLayout);

        jornalEditor.setChangeHandler(() -> {
            editorDialog.close();
            updateListItems(parentId);
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
            jornalEditor.save();
        });

        delete.addClickListener(event -> jornalEditor.deleteJournal());

        jornalEditor.editJournal(car);
        jornalEditor.setSaveButton(save);
        editorDialog.setHeight("600px");
        editorDialog.setWidth("1200px");
        editorDialog.open();
    }

    @Override
    public String getIdView() {
        return ID_VIEW;
    }
}
