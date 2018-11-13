package com.example.demo;

import com.example.demo.entity.cars.car.Car;
import com.example.demo.services.LoginService;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

@HtmlImport("styles/styles.html")
//@Route(value = "main")
@Route(value = "login")
public class MainView extends VerticalLayout implements CarView.Selection {

    LoginService loginService;

    private static final String NAME_OF_MENU_GENERAL = "Основные";
    private static final String MENU_ITEM_LOGOUT = "Выход";
    private static final String ADD_BTN_TEXT = "Добавить";
    private static final String CAR_BTN_TEXT = "Автомобили";
    private static final String JOURNAL_BTN_TEXT = "Журнал";

    private Car selectedCar = null;
    private Map<String, Component> listView = new HashMap<>();
    CarView carView;
    JournalView journalView;

    public MainView(@Autowired LoginService loginService, @Autowired CarView carView, @Autowired JournalView journalView) {
        this.carView = carView;
        this.journalView = journalView;
        this.loginService = loginService;

        carView.selection = this;
        createMenu();
        createActionMenu();
        addMiddleView(carView);
    }

    private void addMiddleView(Component component) {
        String id = ((IdViewable) component).getIdView();
        listView.put(id, component);
        add(component);
    }

    private void createActionMenu() {
        HorizontalLayout menuLayout = new HorizontalLayout();

        Button carBtn = new Button(CAR_BTN_TEXT, VaadinIcon.TRASH.create());
        carBtn.addClickListener(new ComponentEventListener<ClickEvent<Button>>() {
            @Override
            public void onComponentEvent(ClickEvent<Button> event) {
                Component component = listView.get(CarView.ID_VIEW);
                System.out.println(1);
                if (component == null) {
                    System.out.println(2);
                    for (Map.Entry<String, Component> stringComponentMap : listView.entrySet()){
                        remove(stringComponentMap.getValue());
                    }
                    listView.clear();
                    addMiddleView(carView);
                }
            }
        });

        Button journalBtn = new Button(JOURNAL_BTN_TEXT, VaadinIcon.OPEN_BOOK.create());
        journalBtn.addClickListener(new ComponentEventListener<ClickEvent<Button>>() {
            @Override
            public void onComponentEvent(ClickEvent<Button> event) {
                if (selectedCar != null) {
                    Component component = listView.get(JournalView.ID_VIEW);
                    if (component == null) {
                       for (Map.Entry<String, Component> stringComponentMap : listView.entrySet()){
                           remove(stringComponentMap.getValue());

                      }
                        System.out.println(selectedCar.getId());
                           listView.clear();
                        addMiddleView(journalView);
                    }
                }

            }
        });

        menuLayout.add(carBtn, journalBtn);
        add(menuLayout);
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

    @Override
    public void selectItem(Car car) {
        selectedCar = car;
    }
}
